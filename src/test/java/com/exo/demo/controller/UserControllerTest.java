package com.exo.demo.controller;

import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.RessourceNotFoundException;
import com.exo.demo.model.Role;
import com.exo.demo.model.User;
import com.exo.demo.response.ApiResponse;
import com.exo.demo.service.UserService;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import java.util.Collections;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    UserDto user = new UserDto(14L,"adnan","adnan","adnan",
            "adnan@gmail.com","Developper","Ali");
    String NewUser = "{\n" +
            "   \"userid\": 20,\n" +
            "   \"firstName\": \"Ahmed\",\n" +
            "\"lastName\": \"Ahmed\",\n" +
            "        \"email\": \"Ahmed@gmail.com\",\n" +
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
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
      // System.out.println(response.getHeader(HttpHeaders.ACCEPT_LANGUAGE));
        /*assertEquals("http://localhost/users/20",
                response.getHeader(HttpHeaders.LOCATION));*/










    }

    @Test
    void listUsers() throws Exception {
        List<UserDto> allusers = Collections.singletonList(user);
        Mockito.when(
                userService.getUsers()).thenReturn(allusers);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/users/").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String response = result.getResponse().getContentAsString();
        //long id = JsonPath.parse(response).read("result.userId");
        JSONObject json = new JSONObject(response);

        System.out.println("this is the result "+json.getString("result"));
        String expected = "[{\n" +
                "   \"userId\": 14,\n" +
                "   \"firstName\": \"adnan\",\n" +
                "   \"username\": \"adnan\",\n" +
                "\"lastName\": \"adnan\",\n" +
                "        \"email\": \"adnan@gmail.com\",\n" +
                "        \"role\": \"Developper\",\n" +
                "         \"supervisor\": \"Ali\"\n" +
                "}]";


        JSONAssert.assertEquals(expected, json.getString("result"), true);

    }


    @Test
    void getUser() throws Exception {
        Mockito.when(
                userService.findOne(Mockito.anyLong())).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/users/14").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String response = result.getResponse().getContentAsString();
        //long id = JsonPath.parse(response).read("result.userId");
        JSONObject json = new JSONObject(response);

        System.out.println("this is the result "+json.getString("result"));
        String expected = "{\n" +
                "   \"firstName\": \"adnan\",\n" +
                "\"lastName\": \"adnan\",\n" +
                "        \"email\": \"adnan@gmail.com\",\n" +
                "        \"role\": \"Developper\",\n" +
                "         \"supervisor\": \"Ali\"\n" +
                "}";


        JSONAssert.assertEquals(expected, json.getString("result"), false);
    }

    @Test
    void deleteUsers() {
    }

    @Test
    void update() {
    }
}