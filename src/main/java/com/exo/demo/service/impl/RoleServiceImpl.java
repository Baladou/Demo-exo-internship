package com.exo.demo.service.impl;

import com.exo.demo.dao.RoleDao;
import com.exo.demo.dto.UserDto;
import com.exo.demo.model.Role;
import com.exo.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Transactional
@Service("roleService")
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        roleDao.findAll().iterator().forEachRemaining(role -> roles.add(role));
        return roles;
    }

    @Override
    public Role findOneRole(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(long id, Role role) {

    }

    @Override
    public Role createRole(Role role) {
           roleDao.save(role);
        return role;
    }
}
