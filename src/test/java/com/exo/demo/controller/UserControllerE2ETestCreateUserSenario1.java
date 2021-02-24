package com.exo.demo.controller;

import com.exo.demo.Configuration.H2ConfigProfileTest;
import com.exo.demo.DemoApplication;
import com.exo.demo.dao.RoleDao;
import com.exo.demo.dao.UserDao;
import com.exo.demo.exception.NullException;
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
public class UserControllerE2ETestCreateUserSenario1 {

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

    @Before
    public void createRoleDirecteorAndSuperviror() throws Exception {

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

    }

    ////////// Senario1: donner un user sans superviseur
    @Test
    public void Throw_exception_when_createUserWithoutSupervisor() throws Exception {
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
        String uri = "/api/users";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(NewUserObj)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof RessourceExistsException))
                .andExpect(res -> assertEquals("You must insert the supervisor !!", res.getResolvedException().getMessage()))
                .andReturn();


    }

    /////////////////////:Senario 3: creer un user avec un superviseur qui n'existe pas
    @Test
    public void Throw_NotFoundException_when_createUserWithUnexcitingSupervisor() throws Exception {
        String NewUserObjWithUnexsistingSuperviror = "{\n" +
                "        \"firstName\": \"Hala\",\n" +
                "        \"lastName\": \"Hala\",\n" +
                "        \"username\": \"Hala\",\n" +
                "        \"email\": \"Hala@gmail.com\",\n" +
                "         \"role\": {\n" +
                "            \"name\": \"Manager\"\n" +
                "        },\n" +
                "        \"supervisor\":{\"username\": \"Assala\"}\n" +
                "       \n" +
                "}\n" +
                "            ";
        String uri = "/api/users";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(NewUserObjWithUnexsistingSuperviror)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof RessourceNotFoundException))
                .andExpect(res -> assertEquals("Supervisor not found!!", res.getResolvedException().getMessage()))
                .andReturn();


    }

    /////////////////////:Senario 4: creer un user avec un superviseur existant
    @Test
    public void createUserWithExcitingSupervisor() throws Exception {
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
        String uri = "/api/users";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(NewUserObjWithSuperviror)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        String responseString = result.getResponse().getContentAsString();

        JSONObject responsejson = new JSONObject(responseString);

        System.out.println("this is the result " + responsejson);


    }


    /////////////////////:Senario 5: creer un user avec un role qui n'existe pas
    @Test
    public void Throw_NotFoundException_when_createUserWithUnexcitingRole() throws Exception {
        String NewUserObjWithUnexsistingRole = "{\n" +
                "        \"firstName\": \"Hala\",\n" +
                "        \"lastName\": \"Hala\",\n" +
                "        \"username\": \"Hala\",\n" +
                "        \"email\": \"Hala@gmail.com\",\n" +
                "         \"role\": {\n" +
                "            \"name\": \"Tester\"\n" +
                "        },\n" +
                "        \"supervisor\":{\"username\": \"Assala\"}\n" +
                "       \n" +
                "}\n" +
                "            ";
        String uri = "/api/users";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(NewUserObjWithUnexsistingRole)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof RoleNotExistException))
                .andExpect(res -> assertEquals("Role does not exist !!", res.getResolvedException().getMessage()))
                .andReturn();


    }

    /////////////////////:Senario 6: creer un user  sans un role
    @Test
    public void Throw_NotFoundException_when_createUserWithoutRole() throws Exception {
        String NewUserObjWithUnexsistingRole = "{\n" +
                "        \"firstName\": \"Hala\",\n" +
                "        \"lastName\": \"Hala\",\n" +
                "        \"username\": \"Hala\",\n" +
                "        \"email\": \"Hala@gmail.com\",\n" +
                "        \"supervisor\":{\"username\": \"Assala\"}\n" +
                "       \n" +
                "}\n" +
                "            ";
        String uri = "/api/users";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(NewUserObjWithUnexsistingRole)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof RoleNotExistException))
                .andExpect(res -> assertEquals("You must insert the role!!", res.getResolvedException().getMessage()))
                .andReturn();


    }

    /////////////////////:Senario 7: creer un user sans username
    @Test
    public void Throw_Exception_when_createUserWithoutUsername() throws Exception {
        String NewUserObj = "{\n" +
                "        \"firstName\": \"Hamza\",\n" +
                "        \"lastName\": \"Hamza\",\n" +
                "        \"email\": \"Hamza@gmail.com\",\n" +
                "         \"role\": {\n" +
                "            \"name\": \"Manager\"\n" +
                "        },\n" +
                "        \"supervisor\":{\n" +
                "            \"username\": \"Hamza\"}\n" +
                "       \n" +
                "}\n" +
                "            ";

        String uri = "/api/users";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(NewUserObj)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof NullException))
                .andExpect(res -> assertEquals("Username and email must be inserted!!", res.getResolvedException().getMessage()))
                .andReturn();


    }

    /////////////////////:Senario 8: creer un user avec username vide
    @Test
    public void Throw_Exception_when_createUserWithEmpltyUsername() throws Exception {
        String NewUserObj = "{\n" +
                "        \"firstName\": \"Hamza\",\n" +
                "        \"lastName\": \"Hamza\",\n" +
                "        \"username\": \"\",\n" +
                "        \"email\": \"Hamza@gmail.com\",\n" +
                "         \"role\": {\n" +
                "            \"name\": \"Manager\"\n" +
                "        },\n" +
                "        \"supervisor\":{\n" +
                "            \"username\": \"Hamza\"}\n" +
                "       \n" +
                "}\n" +
                "            ";

        String uri = "/api/users";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(NewUserObj)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof NullException))
                .andExpect(res -> assertEquals("Username and email must not be empty!!", res.getResolvedException().getMessage()))
                .andReturn();


    }


}