package com.exo.demo.service;



import com.exo.demo.dto.RoleDto;
import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.RessourceExistsException;
import com.exo.demo.exception.RessourceNotFoundException;
import com.exo.demo.model.Role;

import java.util.List;
import java.util.Optional;
/**
 * @author Baladou
 *
 */
public interface RoleService {

    /**
     * @return
     */
    List<RoleDto> getRoles();

    /**
     *
     * @param id
     * @return
     */
    List<UserDto> getUsersRole(long id) throws RessourceNotFoundException;

    /**
     * @param id
     * @return
     * @throws RessourceNotFoundException
     */


    RoleDto findOneRole(long id) throws RessourceNotFoundException;
    /**
     * @param id
     * @throws RessourceNotFoundException
     */

    void delete(long id) throws RessourceNotFoundException ;
    /**
     * @param id
     * @param roledto
     * @return
     * @throws RessourceNotFoundException
     * @throws RessourceExistsException
     */
    RoleDto update(long id,RoleDto roledto) throws RessourceNotFoundException;

    /**
     * @param role
     * @return
     * @throws RessourceExistsException
     */
    Role createRole(Role role) throws RessourceExistsException;
}
