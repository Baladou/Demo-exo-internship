package com.exo.demo.controller;

import com.exo.demo.Configuration.H2ConfigProfileTest;
import com.exo.demo.DemoApplication;
import com.exo.demo.dao.RoleDao;
import com.exo.demo.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {
        DemoApplication.class,
        H2ConfigProfileTest.class})

@ActiveProfiles("test")
public class UserControllerE2ETest {

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
            "            \"name\": \"Directeur\"\n" +
            "        },\n" +
            "        \"supervisor\":{\n" +
            "            \"username\": \"null\"}\n" +
            "       \n" +
            "}\n" +
            "            ";
    String NewRole = "{\n" +
            "    \"name\": \"directeur\"\n" +
            "}";

    @Test
    public void createUser() throws Exception {

        String url1 = "/api/users";
        String res = mockMvc.perform(MockMvcRequestBuilders.post(url1)
                .content(NewUserObj)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
        //.andExpect(status().isCreated());


    }

    @Test
    public void createRole() throws Exception {
        String url = "/api/roles";

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(NewRole)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


    }

    @Test
    public void getUsers() {

    }
}