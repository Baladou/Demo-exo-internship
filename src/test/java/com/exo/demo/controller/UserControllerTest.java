package com.exo.demo.controller;

import com.exo.demo.model.Role;
import com.exo.demo.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserController userController;

    @Test
    void createUser() {
        //role
        Role role =new Role();
        role.setName("Developper");
        //user
        User user = new User();
        user.setUsername("Ahmed");
        user.setEmail("ahmed@gmail.com");
        user.setLastName("Ahmed");
        user.setFirstName("Ahmed");
        user.setRole(role);
        //user.setUserId();



    }

    @Test
    void listUsers() {
    }

    @Test
    void getUser() {
    }

    @Test
    void deleteUsers() {
    }

    @Test
    void update() {
    }
}