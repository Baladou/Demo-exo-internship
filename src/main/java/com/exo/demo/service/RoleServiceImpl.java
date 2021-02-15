package com.exo.demo.service;

import com.exo.demo.dao.RoleDao;
import com.exo.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service("roleService")
public class RoleServiceImpl  implements RoleService{

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<Role> getRoles() {
        return null;
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
