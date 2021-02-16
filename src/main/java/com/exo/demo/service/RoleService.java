package com.exo.demo.service;



import com.exo.demo.dto.RoleDto;
import com.exo.demo.exception.RessourceExistsException;
import com.exo.demo.exception.RessourceNotFoundException;
import com.exo.demo.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<RoleDto> getRoles();
    RoleDto findOneRole(long id) throws RessourceNotFoundException;
    void delete(long id) throws RessourceNotFoundException ;
    RoleDto update(long id,RoleDto roledto) throws RessourceNotFoundException;
    Role createRole(Role role) throws RessourceExistsException;
}
