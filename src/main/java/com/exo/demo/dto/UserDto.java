package com.exo.demo.dto;


import com.exo.demo.model.User;

import javax.management.relation.Role;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Baladou
 */

public class UserDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Role role;
    private User supervisor;
    private Date createdDate;
    private Date modifiedDate;

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
    public UserDto(long userId, String firstName, String lastName, String username, String email,
                   Role role, User supervisor) {
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.supervisor = supervisor;

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
    public UserDto(Long userId, String firstName, String lastName, String username, String email, Role role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.role = role;
    }
    /////////////Getters

    /**
     * @return
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @return
     */

    public String getFirstName() {
        return firstName;
    }

    /**
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return
     */
    public Role getRole() {
        return role;
    }

    /**
     * @return
     */
    public User getSupervisor() {
        return supervisor;
    }
/////////////////Setters

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @param supervisor
     */
    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }
}
