/**
 *
 */
package com.exo.demo.controller;

import com.exo.demo.dto.RoleDto;

import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.RessourceExistsException;
import com.exo.demo.exception.RessourceNotFoundException;
import com.exo.demo.model.Role;
import com.exo.demo.response.ApiResponse;
import com.exo.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<RoleDto> listRoles() {


        return  roleService.getRoles();
    }
    @GetMapping(value="/{id}")
    public RoleDto getRole(@PathVariable long id) throws RessourceNotFoundException {

        return roleService.findOneRole(id);
    }
    @GetMapping(value="/{id}/users")
    public ApiResponse getUsersRole(@PathVariable long id) throws RessourceNotFoundException {

        return new ApiResponse(HttpStatus.OK, UserController.Status.SUCCESS.name(),roleService.getUsersRole(id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRole(@PathVariable(value = "id") Long id) throws RessourceNotFoundException  {

        roleService.delete(id);

    }

    @PutMapping(value = "/{id}")
    public ApiResponse update(@PathVariable(value = "id") long id, @RequestBody RoleDto roledto) throws RessourceNotFoundException, RessourceExistsException {

        return new ApiResponse(HttpStatus.OK, UserController.Status.SUCCESS.name(),roleService.update(id,roledto)) ;  }


}
