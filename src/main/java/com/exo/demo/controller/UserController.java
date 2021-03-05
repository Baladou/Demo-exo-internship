/**
 *
 */
package com.exo.demo.controller;

import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.*;
import com.exo.demo.response.ApiResponse;
import com.exo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/users")
public class UserController {


    public enum Status {
        SUCCESS, FAILED
    }


    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping
    public ApiResponse createUser(@RequestBody UserDto user) {
        ApiResponse ap = new ApiResponse();
        try {
            ap = new ApiResponse(HttpStatus.CREATED, Status.SUCCESS.name(), userService.createUser(user));
        } catch (Exception e) {
            ap = new ApiResponse(HttpStatus.BAD_REQUEST, Status.FAILED.name(), e);
        }
        return ap;
    }

    @CrossOrigin
    @GetMapping
    public ApiResponse listUsers() {
        return new ApiResponse(HttpStatus.OK, Status.SUCCESS.name(), userService.getUsers());
    }


    @GetMapping(value = "/{id}")
    public ApiResponse getUser(@PathVariable long id) throws RessourceNotFoundException {
        return new ApiResponse(HttpStatus.OK, Status.SUCCESS.name(), userService.findOne(id));
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ApiResponse deleteUsers(@PathVariable(value = "id") Long id) {
        ApiResponse ap = new ApiResponse();
        try {
            userService.delete(id);
            ap = new ApiResponse(HttpStatus.OK, UserController.Status.SUCCESS.name());
        } catch (RessourceNotFoundException e) {
            ap = new ApiResponse(HttpStatus.BAD_REQUEST, UserController.Status.FAILED.name(), e);
        }
        return ap;

    }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ApiResponse update(@PathVariable(value = "id") long id, @RequestBody UserDto user) {

        ApiResponse ap = new ApiResponse();
        try {
            ap = new ApiResponse(HttpStatus.NO_CONTENT, Status.SUCCESS.name(), userService.update(id, user));
        } catch (RessourceExistsException e) {
            ap = new ApiResponse(HttpStatus.BAD_REQUEST, Status.FAILED.name(), e);
        } catch (Exception e) {
            ap = new ApiResponse(HttpStatus.BAD_REQUEST, Status.FAILED.name(), e);
        }
        return ap;
    }

    @CrossOrigin
    @GetMapping("/search")
    public ApiResponse listUsersByFirstName(@RequestParam(value = "firstName") String firstname) {
        return new ApiResponse(HttpStatus.OK, Status.SUCCESS.name(), userService.getUsersByFirstNameLike(firstname));
    }


}
