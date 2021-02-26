package com.exo.demo.service;


import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.*;

import java.util.List;

/**
 * @author Baladou
 */
public interface UserService {

    /**
     * @return
     */

    List<UserDto> getUsers();

    /**
     * @param firstname
     * @return"
     */
    List<UserDto> getUsersByFirstNameLike(String firstname);

    /**
     * @param roleid
     * @return
     */
    List<UserDto> getUsersByRole(String roleid);

    /**
     * @param id
     * @return
     * @throws RessourceNotFoundException
     */

    UserDto findOne(long id) throws RessourceNotFoundException;

    /**
     * @param id
     */
    void delete(long id) throws RessourceNotFoundException;

    /**
     * @param userDto
     * @return
     * @throws RessourceNotFoundException
     * @throws RessourceExistsException
     */
    UserDto update(long id, UserDto userDto) throws RessourceNotFoundException, RessourceExistsException, RoleNotExistException, NothingIsUpdatedException;

    /**
     * @param userDto
     * @return
     * @throws RessourceExistsException
     */
    UserDto createUser(UserDto userDto) throws RessourceExistsException, NullException, RessourceNotFoundException, RoleNotExistException;

}