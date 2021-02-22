package com.exo.demo.service.impl;


import com.exo.demo.dao.UserDao;

import com.exo.demo.model.User;
import com.exo.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserServiceImpl.class)
class UserServiceImplTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDao userDao;

    @MockBean
    private UserService userService;


    @Test
    void getUsers() {

    }

    @Test
    void getUsersByFirstNameLike() {
    }

    @Test
    void getUsersByRole() {
    }

    @Test
    void findOne() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void createUser() {
        ////UserDto NewuserObj = new UserDto(20L, "Ahmed", "Ahmed", "Ahmed",
        //    "Ahmed@gmail.com", "Developper", "Ali");
        /*User user = new User();
        user.setUsername(NewuserObj.getUsername());
        user.setFirstName(NewuserObj.getFirstName());
        user.setLastName(NewuserObj.getLastName());
        user.setRole(new Role(NewuserObj.getRole(), new ArrayList<>()));
        user.setSupervisor(new User());*/

        // UserDto Created = userService.createUser(NewuserObj);
        //Mockito.when(userDao.save(NewuserObj.));


    }
}