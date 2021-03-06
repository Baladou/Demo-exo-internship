package com.exo.demo.service.impl;

import com.exo.demo.dao.RoleDao;
import com.exo.demo.dao.UserDao;
import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.*;
import com.exo.demo.mapper.UserMapper;
import com.exo.demo.model.Role;
import com.exo.demo.model.User;
import com.exo.demo.service.UserService;
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
    public UserDto update(long id, UserDto userDto) throws RessourceExistsException,
            RessourceNotFoundException, RoleNotExistException, NothingIsUpdatedException {
        //trouver l'utilisateur pour le mettre à jour
        User user = userDao.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("User record not found for the id: " + id));
        UserDto OldUserDto = userMapper.toUserDto(user);
        ////tester s'il existe un autre utlisateur avec le meme username ou le meme email
        User Nuser = userDao.findByUsername(userDto.getUsername());
        User Nuser2 = userDao.findByEmail(userDto.getEmail());
        if ((Nuser != null || Nuser2 != null) && Nuser != user && Nuser2 != user)
            throw new RessourceExistsException("Username or Email  already exist!!");


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
        if (userDto.getSupervisor() != null) {
            User supervisor = userDao.findByUsername(userDto.getSupervisor().getUsername());
            if (supervisor != null) {
                user.setSupervisor(supervisor);
            } else {
                throw new RessourceExistsException("Supervisor inserted does not exist!!");
            }
        }
        //cherecher le role
        if (userDto.getRole() != null) {
            Role role = roleDao.findByName(userDto.getRole().getName().toLowerCase());
            if (role != null) {
                user.setRole(role);
            } else {
                throw new RoleNotExistException("Role inserted does not exist!!");
            }
        }

        if (OldUserDto.equals(userMapper.toUserDto(user))) {
            System.out.println(OldUserDto.equals(userMapper.toUserDto(user)));
            throw new NothingIsUpdatedException("We didn't do any update, verify your request body!!");
        }
        /////////enregistrere les modifications
        userDao.save(user);
        userDto.setUserId(userDao.save(user).getUserId());
        return userMapper.toUserDto(user);


    }

    @Override
    public UserDto createUser(UserDto userDto) throws RessourceExistsException, NullException, RessourceNotFoundException, RoleNotExistException {

///////////// tester si username ou l'email sont vides
        if (userDto.getUsername() == null || userDto.getEmail() == null)
            throw new NullException("Username and email must be inserted!!");

        /////////////Tester si username ou l'email sont vides
        if (userDto.getUsername().trim().isEmpty() || userDto.getEmail().trim().isEmpty())
            throw new NullException("Username and email must not be empty!!");

        ///////tester si username existe déja


        User user = userDao.findByUsername(userDto.getUsername());
        if (user != null) throw new RessourceExistsException("Username Already Exists!!");


        ///////tester si l'email existe déja
        user = userDao.findByEmail(userDto.getEmail());
        if (user != null) throw new RessourceExistsException("Email Already Exists!!");

        ///////////// creér le nouveau user


        user = userMapper.toUser(userDto);
        //// trouver le role affecté à l'utilisateur
        if (userDto.getRole() != null) {
            Role role = roleDao.findByName(userDto.getRole().getName().toLowerCase());

            if (role == null) throw new RoleNotExistException("Role does not exist !!");
            user.setRole(role);
        } else {
            throw new RoleNotExistException("You must insert the role!!");
        }
        ///////// trouver le superviseur affecté à l'utilisateur

        if (userDto.getSupervisor() != null) {
            User supervisor = userDao.findByUsername(userDto.getSupervisor().getUsername());
            if (supervisor == null && !userDto.getRole().getName().toLowerCase().equals("directeur")) {

                throw new RessourceNotFoundException("Supervisor not found!!");
            } else {
                user.setSupervisor(supervisor);
            }
        } else {
            if (!userDto.getRole().getName().toLowerCase().equals("directeur"))
                throw new RessourceExistsException("You must insert the supervisor !!");
        }


        user.setCreatedDate(Calendar.getInstance().getTime());
        user.setModifiedDate(Calendar.getInstance().getTime());
        ///////////enregistrer l'utilisateur dans la BD
        User user1 = userDao.save(user);
        userDto.setUserId(user1.getUserId());
        return userMapper.toUserDto(user);
    }


}


