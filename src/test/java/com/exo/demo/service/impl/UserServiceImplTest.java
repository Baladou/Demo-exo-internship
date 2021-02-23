package com.exo.demo.service.impl;


import com.exo.demo.dao.RoleDao;
import com.exo.demo.dao.UserDao;
import com.exo.demo.dto.RoleDto;
import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.NullException;
import com.exo.demo.exception.RessourceExistsException;
import com.exo.demo.exception.RessourceNotFoundException;
import com.exo.demo.mapper.RoleMapper;
import com.exo.demo.mapper.UserMapper;
import com.exo.demo.model.Role;
import com.exo.demo.model.User;
import com.exo.demo.service.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;


    @MockBean
    private UserDao userDao;
    @MockBean
    private RoleDao roleDao;
    UserDto userSupervisorDto = new UserDto("Rajaa", "Rajaa", "Rajaa",
            "Rajaa@gmail.com", new RoleDto("Directeur"));

    @Before
    public void setUp() throws Exception {
        userDao = Mockito.mock(userDao.getClass());

    }

    @Test
    void getUsers() {
        List<User> list = new ArrayList<User>();
        UserDto user1 = new UserDto("Ahmed", "Ahmed", "Ahmed",
                "Ahmed@gmail.com", new RoleDto("Directeur"));

        list.add(userMapper.toUser(user1));

        Mockito.when(userDao.findAll()).thenReturn(list);

        //test
        List<UserDto> UsersList = userService.getUsers();

        assertEquals(1, UsersList.size());
        Mockito.verify(userDao, Mockito.times(1)).findAll();

    }

    @Test
    void getUsersByFirstNameLike() {
    }

    @Test
    void getUsersByRole() {
    }

    @Test
    void findOne() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    //////////sénario 1: creer un directeur sans superviceur
    @Test
    void createUser() throws NullException, RessourceExistsException, RessourceNotFoundException {

        UserDto NewuserObj = new UserDto("Ahmed", "Ahmed", "Ahmed",
                "Ahmed@gmail.com", new RoleDto("directeur"));
        RoleDto roleDto = new RoleDto("Directeur");
        Role role = roleMapper.toRole(roleDto);
        Mockito.when(roleDao.findByName(Mockito.anyString())).thenReturn(role);
        User user = userMapper.toUser(NewuserObj);
        Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(user);
        UserDto Created = userService.createUser(NewuserObj);
        assertEquals(Created.getUsername(), NewuserObj.getUsername());
        assertEquals(Created.getSupervisor(), NewuserObj.getSupervisor());


    }


    ///senario2: Username exist
    @Test
    void createUser_WhenUserNameExist() {
        Assertions.assertThrows(RessourceExistsException.class,
                () -> {
                    UserDto NewuserObj = new UserDto("Hamza", "Ahmed", "Hamza",
                            "Ahmed@gmail.com", new RoleDto(20, "directeur"));
                    RoleDto roleDto = new RoleDto("Directeur");
                    Role role = roleMapper.toRole(roleDto);
                    Mockito.when(roleDao.findByName(Mockito.anyString())).thenReturn(role);
                    User user = userMapper.toUser(NewuserObj);
                    Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(user);
                    Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(user);
                    Mockito.when(userDao.findByEmail(Mockito.anyString())).thenReturn(user);
                    UserDto Created = userService.createUser(NewuserObj);
                });
    }


    ///Sénario 3 : Email exoist

    @Test
    void createUser_WhenEmailExist() {
        Assertions.assertThrows(RessourceExistsException.class,
                () -> {
                    UserDto NewuserObj = new UserDto("Hamza", "Ahmed", "Ahmed",
                            "Hamza@gmail.com", new RoleDto(20, "directeur"));
                    RoleDto roleDto = new RoleDto("Directeur");
                    Role role = roleMapper.toRole(roleDto);
                    Mockito.when(roleDao.findByName(Mockito.anyString())).thenReturn(role);
                    User user = userMapper.toUser(NewuserObj);
                    Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(user);
                    Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(user);
                    Mockito.when(userDao.findByEmail(Mockito.anyString())).thenReturn(user);
                    UserDto Created = userService.createUser(NewuserObj);
                });
    }

    ///sénario 4 : Username non insere
    @Test
    void createUser_WhenUserNameNull() {
        Assertions.assertThrows(NullException.class,
                () -> {
                    UserDto NewuserObj = new UserDto("Ahmed", "Ahmed",
                            "Ahmed@gmail.com", new RoleDto(20, "directeur"));
                    RoleDto roleDto = new RoleDto("Directeur");
                    Role role = roleMapper.toRole(roleDto);
                    Mockito.when(roleDao.findByName(Mockito.anyString())).thenReturn(role);
                    User user = userMapper.toUser(NewuserObj);
                    Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(user);
                    Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(user);
                    Mockito.when(userDao.findByEmail(Mockito.anyString())).thenReturn(user);
                    UserDto Created = userService.createUser(NewuserObj);
                });
    }


    ///sénario 4 : Username vide
    @Test
    void createUser_WhenUserNameVide() throws NullException, RessourceExistsException {
        Assertions.assertThrows(NullException.class,
                () -> {
                    UserDto NewuserObj = new UserDto("Hamza", "Ahmed", "",
                            "Ahmed@gmail.com", new RoleDto(20, "directeur"));
                    RoleDto roleDto = new RoleDto("Directeur");
                    Role role = roleMapper.toRole(roleDto);
                    Mockito.when(roleDao.findByName(Mockito.anyString())).thenReturn(role);
                    User user = userMapper.toUser(NewuserObj);
                    Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(user);
                    Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(user);
                    Mockito.when(userDao.findByEmail(Mockito.anyString())).thenReturn(user);
                    UserDto Created = userService.createUser(NewuserObj);
                });
    }

    //sénario 5 : Email vide
    @Test
    void createUser_WhenEmailVide() {
        Assertions.assertThrows(NullException.class,
                () -> {
                    UserDto NewuserObj = new UserDto("Hamza", "Ahmed", "",
                            "", new RoleDto(20, "directeur"));
                    RoleDto roleDto = new RoleDto("Directeur");
                    Role role = roleMapper.toRole(roleDto);
                    Mockito.when(roleDao.findByName(Mockito.anyString())).thenReturn(role);
                    User user = userMapper.toUser(NewuserObj);
                    Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(user);
                    Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(user);
                    Mockito.when(userDao.findByEmail(Mockito.anyString())).thenReturn(user);
                    UserDto Created = userService.createUser(NewuserObj);
                });
    }

    //sénario 6: Email non insere
    @Test
    void createUser_WhenEmailNull() {
        Assertions.assertThrows(NullException.class,
                () -> {
                    UserDto NewuserObj = new UserDto("Ahmed",
                            new RoleDto(20, "directeur"));
                    RoleDto roleDto = new RoleDto("Directeur");
                    Role role = roleMapper.toRole(roleDto);
                    Mockito.when(roleDao.findByName(Mockito.anyString())).thenReturn(role);
                    User user = userMapper.toUser(NewuserObj);
                    Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(user);
                    Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(user);
                    Mockito.when(userDao.findByEmail(Mockito.anyString())).thenReturn(user);
                    UserDto Created = userService.createUser(NewuserObj);
                });
    }

    //////////sénario 7: donner un role vide
    @Test
    void createUserWithEmptyRole() {
        Assertions.assertThrows(RessourceExistsException.class,
                () -> {
                    UserDto NewuserObj = new UserDto("Ahmed", "Ahmed", "Ahmed",
                            "Ahmed@gmail.com", new RoleDto(""));
                    RoleDto roleDto = new RoleDto("");
                    Role role = roleMapper.toRole(roleDto);
                    Mockito.when(roleDao.findByName(Mockito.anyString())).thenReturn(role);
                    User user = userMapper.toUser(NewuserObj);
                    Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(user);
                    UserDto Created = userService.createUser(NewuserObj);
                    assertEquals(Created.getUsername(), NewuserObj.getUsername());
                    assertEquals(Created.getSupervisor(), NewuserObj.getSupervisor());
                });

    }

    /////////sénario 8: role non insere
    @Test
    void createUserWithoutRole() {
        Assertions.assertThrows(RessourceExistsException.class,
                () -> {
                    UserDto NewuserObj = new UserDto("Ahmed", "Ahmed", "Ahmed",
                            "Ahmed@gmail.com");

                    User user = userMapper.toUser(NewuserObj);
                    Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(user);
                    UserDto Created = userService.createUser(NewuserObj);
                    assertEquals(Created.getUsername(), NewuserObj.getUsername());
                    assertEquals(Created.getSupervisor(), NewuserObj.getSupervisor());
                });

    }

    //////////sénario 7: donner un role qui n'existe pas
    @Test
    void createUserWithUnexcitingRole() {
        Assertions.assertThrows(RessourceNotFoundException.class,
                () -> {
                    UserDto NewuserObj = new UserDto("Ahmed", "Ahmed", "Ahmed",
                            "Ahmed@gmail.com", new RoleDto(""), userSupervisorDto);
                    RoleDto roleDto = new RoleDto("");
                    Role role = roleMapper.toRole(roleDto);
                    Mockito.when(roleDao.findByName(Mockito.anyString())).thenReturn(role);

                    User user = userMapper.toUser(NewuserObj);
                    // User userSupervisor = userMapper.toUser(userSupervisorDto);
                    Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(user);
                    //sMockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(userSupervisor);
                    UserDto Created = userService.createUser(NewuserObj);
                    assertEquals(Created.getUsername(), NewuserObj.getUsername());
                    assertEquals(Created.getSupervisor(), NewuserObj.getSupervisor());
                });

    }
}
