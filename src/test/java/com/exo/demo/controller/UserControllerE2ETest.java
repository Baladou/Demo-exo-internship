package com.exo.demo.controller;

import com.exo.demo.Configuration.H2TestProfileJPAConfig;
import com.exo.demo.DemoApplication;
import com.exo.demo.dao.UserDao;
import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.NullException;
import com.exo.demo.exception.RessourceExistsException;
import com.exo.demo.exception.RessourceNotFoundException;
import com.exo.demo.model.Role;
import com.exo.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        DemoApplication.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
@WebMvcTest(UserController.class)
class UserControllerE2ETest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;
   /* UserDto user = new UserDto(14L, "adnan", "adnan", "adnan",
            "adnan@gmail.com", "Developper", "Ali");
    String NewUser = "{\n" +
            "   \"Username\": \"Ahmed\",\n" +
            "   \"firstName\": \"Ahmed\",\n" +
            "\"lastName\": \"Ahmed\",\n" +
            "        \"email\": \"Ahmed@gmail.com\",\n" +
            "        \"role\": \"Developper\" \n" +
            "}";

    @Test
    void createUser() throws RessourceNotFoundException, RessourceExistsException, NullException {
        UserDto NewuserObj = new UserDto(20L, "Ahmed", "Ahmed", "Ahmed",
                "Ahmed@gmail.com", new Role("Developper"));
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


    }*/
}