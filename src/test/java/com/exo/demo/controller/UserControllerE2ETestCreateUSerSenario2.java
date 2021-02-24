package com.exo.demo.controller;

import com.exo.demo.Configuration.H2ConfigProfileTest;
import com.exo.demo.DemoApplication;
import com.exo.demo.dao.RoleDao;
import com.exo.demo.dao.UserDao;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {
        DemoApplication.class,
        H2ConfigProfileTest.class})

@ActiveProfiles("test")
public class UserControllerE2ETestCreateUSerSenario2 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDao userdao;
    @Autowired
    private RoleDao roledao;


    String NewUserObj = "{\n" +
            "        \"firstName\": \"Hamza\",\n" +
            "        \"lastName\": \"Hamza\",\n" +
            "        \"username\": \"Hamza\",\n" +
            "        \"email\": \"Hamza@gmail.com\",\n" +
            "         \"role\": {\n" +
            "            \"name\": \"directeur\"\n" +
            "        },\n" +
            "        \"supervisor\":{\n" +
            "            \"username\": \"null\"}\n" +
            "       \n" +
            "}\n" +
            "            ";
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

    ////////// Senario1: Ceer un directeur sans superviseur
    @Test
    public void createUser() throws Exception {
        String uri = "/api/users";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(NewUserObj)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        String responseString = result.getResponse().getContentAsString();

        JSONObject responsejson = new JSONObject(responseString);

        System.out.println("this is the result " + responsejson);


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
    @Test
    public void getRole() throws Exception {


        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/roles/").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println(response.getContentAsString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());


    }

    @Test
    public void getUsers() {

    }
}