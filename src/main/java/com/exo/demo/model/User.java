package com.exo.demo.model;



import com.exo.demo.dto.UserDto;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

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
@Getter
@Setter
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



}