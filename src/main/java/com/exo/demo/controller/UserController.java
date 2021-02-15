/**
 *
 */
package com.exo.demo.controller;

import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.UserExistsException;
import com.exo.demo.exception.UserNotFoundException;
import com.exo.demo.service.RoleService;
import com.exo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public UserDto createUser(@RequestBody UserDto user) throws Exception {

        return  userService.createUser(user);
    }



    @GetMapping
    public List<UserDto> listUsers() {


        return  userService.getUsers();
    }
    @GetMapping(value="/{id}")
    public UserDto getUser(@PathVariable long id) throws UserNotFoundException {

        return userService.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUsers(@PathVariable(value = "id") Long id) throws UserNotFoundException {

        userService.delete(id);

    }
    @PutMapping(value = "/{username}")
    public UserDto update(@PathVariable(value = "username") String username,@RequestBody UserDto user) throws UserNotFoundException, UserExistsException {

     return userService.update(username,user) ;  }






}
