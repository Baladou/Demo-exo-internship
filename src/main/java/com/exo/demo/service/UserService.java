package com.exo.demo.service;

import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.UserExistsException;
import com.exo.demo.exception.UserNotFoundException;

import java.util.List;

public interface UserService {


    List<UserDto> getUsers();
    UserDto findOne(long id) throws UserNotFoundException;
    void delete(long id) throws UserNotFoundException;
    UserDto update(String username,UserDto userDto) throws UserNotFoundException,UserExistsException;
    UserDto createUser(UserDto userDto) throws UserExistsException;

}