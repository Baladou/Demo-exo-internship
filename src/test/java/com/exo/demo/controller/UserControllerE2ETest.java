package com.exo.demo.controller;

import com.exo.demo.Configuration.H2ConfigProfileTest;
import com.exo.demo.DemoApplication;

import com.exo.demo.dto.UserDto;
import com.exo.demo.response.ApiResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        DemoApplication.class,
        H2ConfigProfileTest.class})

@ActiveProfiles("test")
public class UserControllerE2ETest {
    @LocalServerPort
    int randomServerPort;

  
    String NewUserObj = "{\n" +
            "   \"Username\": \"Ahmed\",\n" +
            "   \"firstName\": \"Ahmed\",\n" +
            "\"lastName\": \"Ahmed\",\n" +
            "        \"email\": \"Ahmed@gmail.com\",\n" +
            "        \"role\": \"Directeur\" \n" +
            "}";
    @LocalServerPort
    private int port;

    @Test
    public void createUser() throws Exception {


    }

    @Test
    public void getusers() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/users";
        URI uri = new URI(baseUrl);

        ResponseEntity<ApiResponse> result = restTemplate.getForEntity(uri, ApiResponse.class);

        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        // assertEquals(true, result.getBody().contains("employeeList"));
    }
}