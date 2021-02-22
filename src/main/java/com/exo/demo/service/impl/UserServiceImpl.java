package com.exo.demo.service.impl;

import com.exo.demo.dao.RoleDao;
import com.exo.demo.dao.UserDao;
import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.NullException;

import com.exo.demo.exception.RessourceExistsException;
import com.exo.demo.exception.RessourceNotFoundException;

import com.exo.demo.mapper.UserMapper;
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

/**
 * @author Baladou
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<UserDto> getUsers() {
        List<UserDto> userDto = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(user -> userDto.add(userMapper.toUserDto(user)));
        return userDto;
    }

    @Override
    public List<UserDto> getUsersByFirstNameLike(String firstname) {
        List<UserDto> userDto = new ArrayList<>();
        userDao.findByFirstNameContaining(firstname).iterator().forEachRemaining(user -> userDto.add(userMapper.toUserDto(user)));
        return userDto;
    }

    @Override
    public List<UserDto> getUsersByRole(String roleName) {
        List<UserDto> userDto = new ArrayList<>();
        Role role = roleDao.findByName(roleName);
        userDao.findByRoleContaining(role).iterator().forEachRemaining(user -> userDto.add(userMapper.toUserDto(user)));
        return userDto;
    }

    @Override
    public UserDto findOne(long id) throws RessourceNotFoundException {
        User user = userDao.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("User record not found for the id: " + id));

        return userMapper.toUserDto(user);
    }

    @Override
    public void delete(long id) throws RessourceNotFoundException {
        User user = userDao.findById(id).orElseThrow(() -> new RessourceNotFoundException("User record not found for the id: " + id));
        userDao.deleteById(id);
    }

    @Override
    public UserDto update(String username, UserDto userDto) throws RessourceExistsException,
            RessourceNotFoundException {
        //trouver l'utilisateur pour le mettre à jour
        User user = userDao.findByUsername(username);

        ////tester s'il existe un autre utlisateur avec le meme username ou le meme email
        User Nuser = userDao.findByUsername(userDto.getUsername());
        User Nuser2 = userDao.findByEmail(userDto.getUsername());
        if ((Nuser != null || Nuser2 != null) && Nuser != user && Nuser2 != user)
            throw new RessourceExistsException("Username or Email  already exist!!");

        //////////tester si l'utilisateur demandé existe
        if (user == null) throw new RessourceNotFoundException("User not found!!");

        /////////////tester si les champs insers si  ne sont t pas vides pour les mettre à jour

        if (userDto.getUsername() != null && !userDto.getUsername().trim().isEmpty()) {
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getEmail() != null && !userDto.getEmail().trim().isEmpty()) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }

        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }

        user.setModifiedDate(Calendar.getInstance().getTime());
        /////////////cherecher le superviseur
        User supervisor = userDao.findByUsername(userDto.getSupervisor().getUsername());
        if (supervisor != null) {
            user.setSupervisor(supervisor);
        }
        //cherecher le role
        Role role = roleDao.findByName(userDto.getRole().getName());
        if (role != null) {
            user.setRole(role);
        }

        /////////enregistrere les modifications
        userDao.save(user);
        userDto.setUserId(userDao.save(user).getUserId());
        return userMapper.toUserDto(user);


    }

    @Override
    public UserDto createUser(UserDto userDto) throws RessourceExistsException, NullException {

///////////// tester si username ou l'email sont vides
        if (userDto.getUsername() == null || userDto.getEmail() == null)
            throw new NullException("Username and email must be inserted!!");

        /////////////Tester si username ou l'email sont vides
        if (userDto.getUsername().trim().isEmpty() || userDto.getEmail().trim().isEmpty())
            throw new RessourceExistsException("You must add a username and an email!!");

        ///////tester si username existe déja
        User user = userDao.findByUsername(userDto.getUsername());
        if (user != null) throw new RessourceExistsException("Username Already Exists!!");


        ///////tester si l'email existe déja
        user = userDao.findByEmail(userDto.getEmail());
        if (user != null) throw new RessourceExistsException("Email Already Exists!!");

        ///////////// creér le nouveau user
        user = new User();

        BeanUtils.copyProperties(userDto, user);
        //// trouver le role affecté à l'utilisateur
        Role role = roleDao.findByName(userDto.getRole().getName());
        if (role == null) throw new RessourceExistsException("You must insert the role!!");
        user.setRole(role);
        ///////// trouver le superviceur affecté à l'utilisateur
        User supervisor = userDao.findByUsername(userDto.getSupervisor().getUsername());
        if (supervisor == null && !userDto.getRole().getName().toLowerCase().equals("directeur")) {
            throw new RessourceExistsException("You must insert the supervisor !!");
        }
        user.setSupervisor(supervisor);


        user.setCreatedDate(Calendar.getInstance().getTime());
        user.setModifiedDate(Calendar.getInstance().getTime());
        ///////////enregistrer l'utilisateur dans la BD
        User user1 = userDao.save(user);
        userDto.setUserId(user1.getUserId());
        return userDto;
    }


}


