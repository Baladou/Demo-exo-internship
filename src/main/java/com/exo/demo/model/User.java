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


@Entity
@Getter
@Setter
@Table(name = "User")
public class User {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column
    @NonNull
    private String firstName;

    @Column
    @NonNull
    private String lastName;

    @Column
    @NonNull
    private String username;

    @Column
    @NonNull
    private String email;
    @Column
    private Date createdDate;
    @Column
    private Date modifiedDate;

    @ManyToOne()
    @JoinColumn(name="role_id", referencedColumnName = "id", insertable = true, updatable = true)
    private Role role;

    @ManyToOne
    @JoinColumn(name="supervisor_id")
    private User supervisor;







    public UserDto toUserDto() {
        UserDto userDto = new UserDto(this.userId,this.firstName,this.lastName,this.username,
                this.email,this.role.getName(),this.supervisor.getUsername());
        return userDto;
    }



}