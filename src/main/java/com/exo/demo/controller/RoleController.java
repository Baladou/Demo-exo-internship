/**
 *
 */
package com.exo.demo.controller;

import com.exo.demo.dto.UserDto;

import com.exo.demo.exception.RessourceNotFoundException;
import com.exo.demo.model.Role;
import com.exo.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

;import java.util.List;


@RestController
@RequestMapping("api/roles")
public class RoleController {


    @Autowired
    private RoleService roleService;




    @PostMapping
    public Role createRole(@RequestBody Role role) throws Exception {

        return roleService.createRole(role);
    }

    @GetMapping
    public List<Role> listUsers() {


        return  roleService.getRoles();
    }
    @GetMapping(value="/{id}")
    public Role getUser(@PathVariable long id) throws RessourceNotFoundException {

        return roleService.findOneRole(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRole(@PathVariable(value = "id") Long id) throws RessourceNotFoundException {

        roleService.delete(id);

    }




}
