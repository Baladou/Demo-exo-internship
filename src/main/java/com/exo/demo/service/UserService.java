package com.exo.demo.service;

import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.RessourceNotFoundException;
import com.exo.demo.exception.RessourceExistsException;


import java.util.List;
/**
 * @author Baladou
 *
 */
public interface UserService {

    /**
     * @return
     */

    List<UserDto> getUsers();

    /**
     * @param id
     * @return
     * @throws RessourceNotFoundException
     */
    UserDto findOne(long id) throws RessourceNotFoundException;

    /**
     * @param id
     */
    void delete(long id) ;
    /**
     * @param username
     * @param userDto
     * @return
     * @throws RessourceNotFoundException
     * @throws RessourceExistsException
     */
    UserDto update(String username,UserDto userDto) throws RessourceNotFoundException,RessourceExistsException;

    /**
     * @param userDto
     * @return
     * @throws RessourceExistsException
     */
    UserDto createUser(UserDto userDto) throws RessourceExistsException;

}