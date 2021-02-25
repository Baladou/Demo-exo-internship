package com.exo.demo.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Baladou
 */

public class RoleDto {
    private long id;
    private String name;


    public RoleDto() {
        super();
    }

    /**
     * @param id
     * @param name
     */


    public RoleDto(String name) {
        this.name = name;
    }

    /**
     * @param id
     * @param name
     */

    public RoleDto(long id, String name) {
        this.id = id;
        this.name = name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return getName().equals(roleDto.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
