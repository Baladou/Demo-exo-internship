package com.exo.demo.dao;

import com.exo.demo.dto.UserDto;
import com.exo.demo.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
class UserDaoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserDao userdao;
   /* @Test
    void findByUsername() {
// given
        UserDto user = new UserDto();
        user.setUsername("Zineb");
       /* user.setEmail("Zineb@gmail.com");
        user.setLastName("Zineb");
        user.setFirstName("Zineb");
        //user.setRole("developper");
        user.setUserId(20L);

        entityManager.persist(user);
        entityManager.flush();


        User found = userdao.findByUsername(user.getUsername());


        assertThat(found.getUsername())
                .isEqualTo(user.getUsername());
        assertEquals(found.getUsername(),user.getUsername(),"good match");
    }
*/
    @Test
    void findByEmail() {
    }
}