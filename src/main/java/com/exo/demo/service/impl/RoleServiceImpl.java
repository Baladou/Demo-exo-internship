package com.exo.demo.service.impl;

import com.exo.demo.dao.RoleDao;
import com.exo.demo.dto.RoleDto;
import com.exo.demo.exception.RessourceExistsException;
import com.exo.demo.exception.RessourceNotFoundException;
import com.exo.demo.model.Role;
import com.exo.demo.model.User;
import com.exo.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service("roleService")
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<RoleDto> getRoles() {
        List<RoleDto> roles = new ArrayList<>();
        roleDao.findAll().iterator().forEachRemaining(role -> roles.add(role.toRoleDto()));
        return roles;
    }

    @Override
    public RoleDto findOneRole(long id) throws RessourceNotFoundException {

        Role role =roleDao.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Role  not found for the id: " + id));
       return role.toRoleDto();
    }

    @Override
    public void delete(long id) throws RessourceNotFoundException {
        RoleDto role= findOneRole(id);
        if(role.getUsers() != null) throw new RessourceNotFoundException(" You can not delete this role because it is used by other ressources!!");
        roleDao.deleteById(id);
    }

    @Override
    public RoleDto update(long id, RoleDto roledto) throws RessourceNotFoundException{
        Role role =roleDao.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Role  not found for the id: " + id));
        role.setName(roledto.getName());




        return null;


    }

    @Override
    public Role createRole(Role role) {
           roleDao.save(role);
        return role;
    }
}
