package com.exo.demo.service;


import com.exo.demo.exception.UserExistsException;
import com.exo.demo.exception.UserNotFoundException;
import com.exo.demo.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();
    Role findOneRole(long id) ;
    void delete(long id);
    void update(long id,Role role) ;
    Role createRole(Role role) ;
}
