package com.exo.demo.controller;

import com.exo.demo.Configuration.H2ConfigProfileTest;
import com.exo.demo.DemoApplication;
import com.exo.demo.dao.RoleDao;
import com.exo.demo.dao.UserDao;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {
        DemoApplication.class,
        H2ConfigProfileTest.class})

@ActiveProfiles("test")
public class UserControllerUpdateE2ETest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDao userdao;
    @Autowired
    private RoleDao roledao;
    String SupervisorObj = "{\n" +
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
    String NewUserObjWithSuperviror = "{\n" +
            "        \"firstName\": \"Hala\",\n" +
            "        \"lastName\": \"Hala\",\n" +
            "        \"username\": \"Hala\",\n" +
            "        \"email\": \"Hala@gmail.com\",\n" +
            "         \"role\": {\n" +
            "            \"name\": \"Manager\"\n" +
            "        },\n" +
            "        \"supervisor\":{\"username\": \"Hamza\"}\n" +
            "       \n" +
            "}\n" +
            "            ";

    @Before
    public void createRoleDirecteorAndSupervirorAndUsetToUpdate() throws Exception {

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
        String uri = "/api/users";
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(SupervisorObj)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response1 = result1.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response1.getStatus());


        MvcResult result2 = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(NewUserObjWithSuperviror)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response2 = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response2.getStatus());


    }

    @Test
    public void update() throws Exception {
        String uri = "/api/users/4";
        String Obj = "{\n" +
                "         \"firstName\":\"Wafaa\"\n" +
                "}";
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .content(Obj)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response1 = result1.getResponse();
        assertEquals(HttpStatus.OK.value(), response1.getStatus());
        String responseString = result1.getResponse().getContentAsString();

        JSONObject responsejson = new JSONObject(responseString);
        System.out.println(responsejson.getJSONObject("result").getString("firstName"));
        assertEquals("Wafaa", responsejson.getJSONObject("result").getString("firstName"));
        assertEquals("Hala", responsejson.getJSONObject("result").getString("username"));


    }

}
