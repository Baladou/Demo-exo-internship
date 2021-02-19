package com.exo.demo.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Baladou
 */

public class RoleDto {
    private long id;
    private String name;
    private List<String> users = new ArrayList<>();

    public RoleDto() {
        super();
    }

    /**
     * @param id
     * @param name
     */

    public RoleDto(long id, String name) {
        super();
        this.id = id;
        this.name = name;

    }

    /**
     * @param id
     * @param name
     * @param users
     */
    public RoleDto(long id, String name, List<String> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    ////Getters

    /**
     * @return
     */
    public long getId() {
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
    public List<String> getUsers() {
        return users;
    }
///////////////////Setters

    /**
     * @param id
     */
    public void setId(long id) {
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
    public void setUsers(List<String> users) {
        this.users = users;
    }
}
