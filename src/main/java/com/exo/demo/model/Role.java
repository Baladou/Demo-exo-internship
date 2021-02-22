package com.exo.demo.model;

import com.exo.demo.dao.RoleDao;
import com.exo.demo.dto.RoleDto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Baladou
 */


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

    @OneToMany(targetEntity = User.class, mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(@NonNull String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    /**
     * @return
     */
   /* public RoleDto toRoleDto() {
        RoleDto roledto = new RoleDto(this.id, this.name);
        roledto.setUsers(this.users.stream().map(user ->
                user.getUsername().toString()).collect(Collectors.toList()));

        return roledto;
    }*/
    /////////////Getters

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    public List<User> getUsers() {
        return users;
    }

    ///////////Setters

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
}