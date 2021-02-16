package com.exo.demo.dto;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String username;

    private String email;
    private String role;
    private String supervisor;

    public UserDto() {
        super();
    }



    public UserDto(long userId, String firstName, String lastName, String username,  String email,
                   String role,String supervisor) {
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.supervisor=supervisor;

        this.email = email;
        this.role = role;
    }

    public UserDto(Long userId, String firstName, String lastName, String username, String email, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
