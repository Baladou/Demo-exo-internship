package com.exo.demo.model;



import com.exo.demo.dto.UserDto;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
        * @author Baladou
        *
        */
@Entity

@Table(name = "User")
public class User {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String username;


    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Date createdDate;
    @Column
    private Date modifiedDate;

    @ManyToOne()
    @JoinColumn(name="role_id", referencedColumnName = "id", insertable = true, updatable = true)
    private Role role;

    @ManyToOne
    @JoinColumn(name="supervisor_id",insertable = true, updatable = true)
    private User supervisor;

    /**
     *
     * @return
     */

    public UserDto toUserDto() {
        UserDto userDto=new UserDto();
        if(this.supervisor!= null){
         userDto = new UserDto(this.userId,this.firstName,this.lastName,this.username,
                this.email,this.role.getName(),this.supervisor.getUsername());}
        else{
            userDto = new UserDto(this.userId,this.firstName,this.lastName,this.username,
                    this.email,this.role.getName());
        }
        return userDto;
    }

 //////////Getters

    /**
     *
     * @return
     */
    public Long getUserId() {
        return userId;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     *
     * @return
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     *
     * @return
     */
    public Role getRole() {
        return role;
    }

    /**
     *
     * @return
     */
    public User getSupervisor() {
        return supervisor;
    }

    ///////////Setters

    /**
     *
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     *
     * @param modifiedDate
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     *
     * @param role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     *
     * @param supervisor
     */
    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }
}