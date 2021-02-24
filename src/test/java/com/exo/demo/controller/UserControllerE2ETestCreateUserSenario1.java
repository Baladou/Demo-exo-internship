package com.exo.demo.controller;

import com.exo.demo.Configuration.H2ConfigProfileTest;
import com.exo.demo.DemoApplication;
import com.exo.demo.dao.RoleDao;
import com.exo.demo.dao.UserDao;
import com.exo.demo.exception.NullException;
import com.exo.demo.exception.RessourceExistsException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {
        DemoApplication.class,
        H2ConfigProfileTest.class})

@ActiveProfiles("test")
public class UserControllerE2ETestCreateUserSenario1 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDao userdao;
    @Autowired
    private RoleDao roledao;


    String NewUserObj = "{\n" +
            "        \"firstName\": \"Hala\",\n" +
            "        \"lastName\": \"Hala\",\n" +
            "        \"username\": \"Hala\",\n" +
            "        \"email\": \"Hala@gmail.com\",\n" +
            "         \"role\": {\n" +
            "            \"name\": \"Manager\"\n" +
            "        }\n" +
            "       \n" +
            "}\n" +
            "             ";
    String NewRole = "{\n" +
            "    \"name\": \"directeur\"\n" +
            "}";
    String NewRole1 = "{\n" +
            "    \"name\": \"manager\"\n" +
            "}";
    String NewRole2 = "{\n" +
            "    \"name\": \"developper\"\n" +
            "}";

    @Before
    public void createRoleDirecteor() throws Exception {

        //list = new ArrayList<>(Arrays.asList("test1", "test2"));
        String url = "/api/roles";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(NewRole)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(NewRole1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println(response.getContentAsString());
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }

    ////////// Senario2: donner un user sans superviseur
    @Test
    public void Throw_exception_when_createUserWithoutSupervisor() throws Exception {

        String uri = "/api/users";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(NewUserObj)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof RessourceExistsException))
                .andExpect(res -> assertEquals("You must insert the supervisor !!", res.getResolvedException().getMessage()))

                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        //System.out.println(response.getStatus());
        //assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());


    }


    /*
        @Test
        public void createRole() throws Exception {
            String url = "/api/roles";

            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .content(NewRole)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
            MockHttpServletResponse response = result.getResponse();
            System.out.println(response.getContentAsString());
            assertEquals(HttpStatus.CREATED.value(), response.getStatus());


        }
*/


}
