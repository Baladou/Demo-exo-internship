package com.exo.demo.service.impl;

import com.exo.demo.dao.RoleDao;
import com.exo.demo.dto.RoleDto;

import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.RessourceExistsException;
import com.exo.demo.exception.RessourceNotFoundException;

import com.exo.demo.exception.RoleNotFoundException;
import com.exo.demo.mapper.RoleMapper;
import com.exo.demo.mapper.UserMapper;
import com.exo.demo.model.Role;
import com.exo.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<RoleDto> getRoles() {
        List<RoleDto> roles = new ArrayList<>();
        roleDao.findAll().iterator().forEachRemaining(role -> roles.add(roleMapper.toRoleDto(role)));
        return roles;
    }

    @Override
    public List<UserDto> getUsersRole(long id) throws RessourceNotFoundException {
        List<UserDto> usersDto = new ArrayList<>();
        Role role = roleDao.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Role  not found for the id: " + id));
        role.getUsers().iterator().forEachRemaining(user -> usersDto.add(userMapper.toUserDto(user)));
        return usersDto;
    }

    @Override
    public RoleDto findOneRole(long id) throws RessourceNotFoundException {

        Role role = roleDao.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Role  not found for the id: " + id));
        return roleMapper.toRoleDto(role);

    }

    @Override
    public void delete(long id) throws RoleNotFoundException {
        Role role = roleDao.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role  not found for the id: " + id));
        if (role.getUsers().size() != 0)
            throw new RoleNotFoundException("You can not delete this role because it is used by other ressources!!");
        roleDao.deleteById(id);
    }

    @Override
    public RoleDto update(long id, RoleDto roledto) throws RessourceNotFoundException, RessourceExistsException {
        Role role = roleDao.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Role  not found for the id: " + id));
        if (roleDao.findByName(roledto.getName()) != null)
            throw new RessourceExistsException("The role inserted already exists in the DB");
        role.setName(roledto.getName());
        return roleMapper.toRoleDto(role);


    }

    @Override
    public Role createRole(Role role) throws RessourceExistsException {
        if (roleDao.findByName(role.getName()) != null)
            throw new RessourceExistsException("The role inserted already exists in the DB");
        roleDao.save(role);
        return role;
    }
}