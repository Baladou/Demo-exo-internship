package com.exo.demo.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class RoleDto {
    private long id;
    private String name;
    private List<String> users = new ArrayList<>();

    public RoleDto() {
        super();
    }

    public RoleDto(long id, String name) {
        super();
        this.id = id;
        this.name = name;

    }

    public RoleDto(long id, String name, List<String> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }
}
