/**
 *
 */
package com.exo.demo.controller;

import com.exo.demo.dto.RoleDto;
import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.RessourceExistsException;
import com.exo.demo.exception.RessourceNotFoundException;
import com.exo.demo.exception.RoleNotFoundException;
import com.exo.demo.model.Role;
import com.exo.demo.response.ApiResponse;
import com.exo.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/roles")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @CrossOrigin
    @PostMapping
    public ApiResponse createRole(@RequestBody Role role) throws Exception {

        ApiResponse ap = new ApiResponse();
        try {
            ap = new ApiResponse(HttpStatus.CREATED, UserController.Status.SUCCESS.name(), roleService.createRole(role));
        } catch (Exception e) {
            ap = new ApiResponse(HttpStatus.BAD_REQUEST, UserController.Status.FAILED.name(), e);
        }
        return ap;
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<RoleDto>> listRoles() {


        return new ResponseEntity<List<RoleDto>>(roleService.getRoles(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public RoleDto getRole(@PathVariable long id) throws RessourceNotFoundException {

        return roleService.findOneRole(id);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}/users")
    public ApiResponse getUsersRole(@PathVariable long id) throws RessourceNotFoundException {

        return new ApiResponse(HttpStatus.OK, UserController.Status.SUCCESS.name(), roleService.getUsersRole(id));
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ApiResponse deleteRole(@PathVariable(value = "id") Long id) {
        ApiResponse ap = new ApiResponse();
        try {
            roleService.delete(id);
            ap = new ApiResponse(HttpStatus.OK, UserController.Status.SUCCESS.name());
        } catch (RoleNotFoundException e) {
            ap = new ApiResponse(HttpStatus.BAD_REQUEST, UserController.Status.FAILED.name(), e);
        }
        return ap;
    }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ApiResponse update(@PathVariable(value = "id") long id, @RequestBody RoleDto roledto) throws RessourceNotFoundException, RessourceExistsException {

        return new ApiResponse(HttpStatus.OK, UserController.Status.SUCCESS.name(), roleService.update(id, roledto));
    }


}
