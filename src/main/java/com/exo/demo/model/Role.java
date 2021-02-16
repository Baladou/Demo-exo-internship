package com.exo.demo.model;

import com.exo.demo.dao.RoleDao;
import com.exo.demo.dto.RoleDto;
import com.exo.demo.dto.UserDto;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "Role")
public class Role {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NonNull
    private String name;

    @OneToMany(targetEntity=User.class, mappedBy="role",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();



    public RoleDto toRoleDto() {
        RoleDto roledto = new RoleDto(this.id,this.name);
        roledto.setUsers(this.users.stream().map(user -> user.getUsername().toString()).collect(Collectors.toList()));

        //System.out.println(roledto.getUsers());

        return roledto;
    }
}