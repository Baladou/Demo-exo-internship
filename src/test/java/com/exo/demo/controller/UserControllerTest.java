package com.exo.demo.controller;

import com.exo.demo.dao.UserDao;
import com.exo.demo.dto.UserDto;
import com.exo.demo.service.UserService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
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

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDao userDao;

    @MockBean
    private UserService userService;

    UserDto user = new UserDto(14L, "adnan", "adnan", "adnan",
            "adnan@gmail.com", "Developper", "Ali");
    String NewUser = "{\n" +
            "   \"Username\": \"Ahmed\",\n" +
            "   \"firstName\": \"Ahmed\",\n" +
            "\"lastName\": \"Ahmed\",\n" +
            "        \"email\": \"Ahmed@gmail.com\",\n" +
            "        \"role\": \"Developper\" \n" +
            "}";

    @Test
    void createUser() throws Exception {
        UserDto NewuserObj = new UserDto(20L, "Ahmed", "Ahmed", "Ahmed",
                "Ahmed@gmail.com", "Developper");
        Mockito.when(
                userService.createUser(Mockito.any(UserDto.class))).thenReturn(NewuserObj);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users")
                .accept(MediaType.APPLICATION_JSON).content(NewUser)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println(response.getContentAsString());
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());


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

        System.out.println("this is the result " + json.getString("result"));
        String expected = "[{\n" +
                "   \"userId\": 14,\n" +
                "   \"firstName\": \"adnan\",\n" +
                "   \"username\": \"adnan\",\n" +
                "\"lastName\": \"adnan\",\n" +
                "        \"email\": \"adnan@gmail.com\",\n" +
                "        \"role\": \"Developper\",\n" +
                "         \"supervisor\": \"Ali\"\n" +
                "}]";


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

        System.out.println("this is the result " + json.getString("result"));
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
    void deleteUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/14"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        JSONObject json = new JSONObject(content);

        assertEquals("SUCCESS", json.getString("message"));

    }

    @Test
    void update() throws Exception {
        UserDto NewuserObj = new UserDto(14L, "Ahmed", "Ahmed", "Ahmed",
                "Ahmed@gmail.com", "Developper");
        String uri = "/api/users/Ahmed";
        Mockito.when(
                userService.update(Mockito.anyString(), Mockito.any(UserDto.class))).thenReturn(NewuserObj);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(NewUser)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        JSONObject json = new JSONObject(content);

        String expected = "{\"userId\": 14,\n" +
                "   \"firstName\": \"Ahmed\",\n" +
                "   \"username\": \"Ahmed\",\n" +
                "\"lastName\": \"Ahmed\",\n" +
                "        \"email\": \"Ahmed@gmail.com\",\n" +
                "        \"role\": \"Developper\",\n" +
                "         \"supervisor\": null \n" +
                "}";
        JSONAssert.assertEquals(expected, json.getString("result"), false);
    }
}