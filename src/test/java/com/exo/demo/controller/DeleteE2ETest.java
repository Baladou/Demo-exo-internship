package com.exo.demo.controller;

import com.exo.demo.Configuration.H2ConfigProfileTest;
import com.exo.demo.DemoApplication;
import com.exo.demo.exception.RoleNotFoundException;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {
        DemoApplication.class,
        H2ConfigProfileTest.class})

@ActiveProfiles("test")
public class DeleteE2ETest {
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
    public void createRoleDirecteorAndSupervirorAndUsetToDelete() throws Exception {

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
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(NewRole2)
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


    @Test
    public void deleteUserById() throws Exception {
        String uri = "/api/users/4";
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response1 = result1.getResponse();
        assertEquals(HttpStatus.OK.value(), response1.getStatus());


    }

    @Test
    public void deleteRoleById() throws Exception {
        String uri = "/api/roles/3";
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response1 = result1.getResponse();
        assertEquals(HttpStatus.OK.value(), response1.getStatus());


    }

    @Test
    public void ThrowException_When_deleteRoleById_IdNotFound() throws Exception {
        String uri = "/api/roles/6";
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof RoleNotFoundException))
                .andExpect(res -> assertEquals("Role  not found for the id: 6", res.getResolvedException().getMessage()))
                .andExpect(status().isBadRequest())
                .andReturn();
        

    }

    @Test
    public void ThrowException_When_deleteRoleById_RoleAffectedToUser() throws Exception {
        String uri = "/api/roles/2";
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof RoleNotFoundException))
                .andExpect(res -> assertEquals("You can not delete this role because it is used by other ressources!!", res.getResolvedException().getMessage()))
                .andReturn();
        MockHttpServletResponse response1 = result1.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response1.getStatus());


    }

    @Test
    public void getRoles() throws Exception {


        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/roles/").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println(response.getContentAsString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());


    }
}
