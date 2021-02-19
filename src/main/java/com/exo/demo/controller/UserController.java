/**
 *
 */
package com.exo.demo.controller;

import com.exo.demo.dto.UserDto;

import com.exo.demo.exception.RessourceExistsException;
import com.exo.demo.exception.RessourceNotFoundException;
import com.exo.demo.response.ApiResponse;
import com.exo.demo.service.RoleService;
import com.exo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/users")
public class UserController {


    public enum Status {
        SUCCESS, FAILED
    }


    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) throws Exception {

        return  new ResponseEntity<UserDto>(userService.createUser(user), HttpStatus.CREATED);
    }



    @GetMapping
    public ApiResponse listUsers() {
        return new ApiResponse(HttpStatus.OK, Status.SUCCESS.name(), userService.getUsers());
    }


    @GetMapping(value="/{id}")
    public ApiResponse getUser(@PathVariable long id) throws RessourceNotFoundException {
        return new ApiResponse(HttpStatus.OK, Status.SUCCESS.name(), userService.findOne(id));
    }

    @DeleteMapping(value = "/{id}")
    public ApiResponse deleteUsers(@PathVariable(value = "id") Long id) throws RessourceNotFoundException {
        userService.delete(id);
        return new ApiResponse(HttpStatus.OK, Status.SUCCESS.name());

    }
    @PutMapping(value = "/{username}")
    public ApiResponse update(@PathVariable(value = "username") String username,@RequestBody UserDto user) throws RessourceNotFoundException, RessourceExistsException {

     return new ApiResponse(HttpStatus.OK, Status.SUCCESS.name(),userService.update(username,user)) ;  }


    @GetMapping("/search")
    public ApiResponse listUsersByFirstName(@RequestParam(value = "firstName" ) String firstname) {
        return new ApiResponse(HttpStatus.OK, Status.SUCCESS.name(), userService.getUsersByFirstNameLike(firstname));
    }



}
