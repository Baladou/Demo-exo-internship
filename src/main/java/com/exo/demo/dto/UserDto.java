package com.exo.demo.dto;



import lombok.Getter;
import lombok.Setter;

/**
 * @author Baladou
 *
 */
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

    /**
     * @param userId
     * @param firstName
     * @param lastName
     * @param username
     * @param email
     * @param role
     * @param supervisor
     */

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
    /**
     * @param userId
     * @param firstName
     * @param lastName
     * @param username
     * @param email
     * @param role
     */
    public UserDto(Long userId, String firstName, String lastName, String username, String email, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
