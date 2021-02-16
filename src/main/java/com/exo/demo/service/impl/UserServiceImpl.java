package com.exo.demo.service.impl;

import com.exo.demo.dao.RoleDao;
import com.exo.demo.dao.UserDao;
import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.RessourceExistsException;
import com.exo.demo.exception.RessourceNotFoundException;

import com.exo.demo.model.Role;
import com.exo.demo.model.User;
import com.exo.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;





    @Override
    public List<UserDto> getUsers() {
        List<UserDto> userDto = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(user -> userDto.add(user.toUserDto()));
        return userDto;
    }

    @Override
    public UserDto findOne(long id) throws RessourceNotFoundException {
        User user = userDao.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("User record not found for the id: " + id));

        return user.toUserDto();
    }

    @Override
    public void delete(long id) throws RessourceNotFoundException {
        userDao.deleteById(id);
    }

    @Override
    public UserDto update(String username, UserDto userDto) throws RessourceExistsException,
            RessourceNotFoundException{
        User user = userDao.findByUsername(username);
        if(user == null) throw new RessourceNotFoundException("User not found!!");


        BeanUtils.copyProperties(userDto, user);

        Role role= roleDao.findByName(userDto.getRole());
        if(role == null) throw new RessourceExistsException("Role does not  Exist!!");
        user.setRole(role);
        user.setModifiedDate(Calendar.getInstance().getTime());

        userDao.save(user);

        return userDto;


    }

    @Override
    public UserDto createUser(UserDto userDto) throws RessourceExistsException {
        User user = userDao.findByUsername(userDto.getUsername());
        if (user != null)  throw new RessourceExistsException("User name already exists!!");

        user = userDao.findByEmail(userDto.getEmail());
        if (user != null)  throw new RessourceExistsException("Email Already Exists!!");

        user = new User();

        BeanUtils.copyProperties(userDto, user);

        Role role= roleDao.findByName(userDto.getRole());
        if(role == null) throw new RessourceExistsException("Role does not  Exist!!");
        user.setRole(role);



        user.setCreatedDate(Calendar.getInstance().getTime());
        user.setModifiedDate(Calendar.getInstance().getTime());

        User user1 = userDao.save(user);
        userDto.setUserId(user1.getUserId());
        return userDto;
    }


}
