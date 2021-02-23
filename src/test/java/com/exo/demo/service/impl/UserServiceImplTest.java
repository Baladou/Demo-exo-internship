package com.exo.demo.service.impl;


import com.exo.demo.dao.UserDao;
import com.exo.demo.dto.RoleDto;
import com.exo.demo.dto.UserDto;
import com.exo.demo.exception.NullException;
import com.exo.demo.exception.RessourceExistsException;
import com.exo.demo.mapper.UserMapper;
import com.exo.demo.model.User;
import com.exo.demo.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

    @InjectMocks
    private UserService userService;
    @InjectMocks
    private UserMapper userMapper;

    @Mock
    private UserDao userDao;


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

    @Test
    void createUser() throws NullException, RessourceExistsException {
        UserDto NewuserObj = new UserDto(null, "Ahmed", "Ahmed", "Ahmed",
                "Ahmed@gmail.com", new RoleDto("Directeur"));
        User user = userMapper.toUser(NewuserObj);

        /*User user = new User();
        user.setUsername(NewuserObj.getUsername());
        user.setLastName(NewuserObj.getLastName());
        user.setFirstName(NewuserObj.getFirstName());
        user.setEmail(NewuserObj.getEmail());
        user.setRole(new Role(NewuserObj.getRole().getName()));*/
        System.out.println(user.getUsername());
        Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(user);
        // UserDto Created = userService.createUser(NewuserObj);
        System.out.println(NewuserObj.getUsername());
        //assertEquals(Created.getUsername(), NewuserObj.getUsername());


    }
}