package com.exo.demo.controller;

import com.exo.demo.Configuration.H2ConfigProfileTest;
import com.exo.demo.DemoApplication;
import com.exo.demo.dao.RoleDao;
import com.exo.demo.dao.UserDao;
import com.exo.demo.exception.NothingIsUpdatedException;
import com.exo.demo.exception.RessourceExistsException;
import com.exo.demo.exception.RessourceNotFoundException;
import com.exo.demo.exception.RoleNotExistException;
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
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {
        DemoApplication.class,
        H2ConfigProfileTest.class})

@ActiveProfiles("test")
public class UserControllerUpdateE2ETest {
    @Autowired
    private MockMvc mockMvc;

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

        ////Creer les roles
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
        //System.out.println(response.getContentAsString());
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        //creer le superviseur
        String uri = "/api/users";
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(SupervisorObj)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response1 = result1.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response1.getStatus());

        //Creer User qu'on veut le  mettre à jour
        MvcResult result2 = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(NewUserObjWithSuperviror)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response2 = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response2.getStatus());


    }

    //////Senario1: update firstName
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

    //////Senario2: update Username avec un username qui exist déja
    @Test
    public void ThrowException_When_update_UsernameWithExistingOne() throws Exception {
        String uri = "/api/users/4";
        String Obj = "{\n" +
                "         \"username\":\"Hamza\"\n" +
                "}";
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .content(Obj)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof RessourceExistsException))
                .andExpect(res -> assertEquals("Username or Email  already exist!!", res.getResolvedException().getMessage()))
                .andReturn();


    }

    //////Senario3: update Email avec un email qui exist déja
    @Test
    public void ThrowException_When_update_EmailWithExistingOne() throws Exception {
        String uri = "/api/users/4";
        String Obj = "{\n" +
                "         \"email\":\"Hamza@gmail.com\"\n" +
                "}";
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .content(Obj)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof RessourceExistsException))
                .andExpect(res -> assertEquals("Username or Email  already exist!!", res.getResolvedException().getMessage()))
                .andReturn();

    }

    //////Senario4: update Role avec un superviseur qui n'existe pas
    @Test
    public void ThrowException_When_update_WithUnExistingSupervisor() throws Exception {
        String uri = "/api/users/4";
        String Obj = "{\"supervisor\": {\n" +
                "    \"username\": \"Tester\"\n" +
                "}}";
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .content(Obj)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof RessourceExistsException))
                .andExpect(res -> assertEquals("Supervisor inserted does not exist!!", res.getResolvedException().getMessage()))
                .andReturn();


    }

    //////Senario5: update Role avec un role qui n'existe pas
    @Test
    public void ThrowException_When_update_WithUnExistingRole() throws Exception {
        String uri = "/api/users/4";
        String Obj = "{\"role\": {\n" +
                "    \"name\": \"Tester\"\n" +
                "}}";
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .content(Obj)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof RoleNotExistException))
                .andExpect(res -> assertEquals("Role inserted does not exist!!", res.getResolvedException().getMessage()))
                .andReturn();


    }

    /////
//////Senario6: Verifier si les changement sont fait(probleme de body)
    @Test
    public void ThrowException_When_NoUpdate_IsDone() throws Exception {
        String uri = "/api/users/4";
        String Obj = "{\"userName\" : \"ahmed\"}";
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .content(Obj)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof NothingIsUpdatedException))
                .andExpect(res -> assertEquals("We didn't do any update, verify your request body!!", res.getResolvedException().getMessage()))
                .andReturn();


    }

    //////Senario7: id  user n'existe pas
    @Test
    public void ThrowException_When_UserToUpdate_NotExist() throws Exception {
        String uri = "/api/users/5";
        String Obj = "{\"username\" : \"ahmed\"}";
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .content(Obj)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof RessourceNotFoundException))
                .andExpect(res -> assertEquals("User record not found for the id: 5", res.getResolvedException().getMessage()))
                .andReturn();


    }

}
