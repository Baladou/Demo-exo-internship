package com.exo.demo.controller;

import com.exo.demo.dao.UserDao;
import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.NullException;
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

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTestRG {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserService userService;

    UserDto user = new UserDto(14L,"adnan","adnan","adnan",
            "adnan@gmail.com","Developper","Ali");
    String NewUser = "{\n" +
            "   \"firstName\": \"Ahmed\",\n" +
            "\"lastName\": \"Ahmed\",\n" +
            "        \"role\": \"Developper\" \n" +
            "}";

    @Test
    void createUser() throws Exception {
        UserDto NewuserObj = new UserDto(20L,"Ahmed","Ahmed","Ahmed",
                "Ahmed@gmail.com","Developper");
        Mockito.when(
                userService.createUser(Mockito.any(UserDto.class))).thenReturn(NewuserObj);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users")
                .accept(MediaType.APPLICATION_JSON).content(NewUser)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();

       System.out.println("this is the results "+response.getContentAsString());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
        //assertEquals("success",response.getContentAsString());



    }
    @Test
    void update() {
    }
}