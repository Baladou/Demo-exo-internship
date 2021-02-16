package com.exo.demo.service;

import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.RessourceNotFoundException;
import com.exo.demo.exception.RessourceExistsException;


import java.util.List;

public interface UserService {


    List<UserDto> getUsers();
    UserDto findOne(long id) throws RessourceNotFoundException;
    void delete(long id) ;
    UserDto update(String username,UserDto userDto) throws RessourceNotFoundException,RessourceExistsException;
    UserDto createUser(UserDto userDto) throws RessourceExistsException;

}