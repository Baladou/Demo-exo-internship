package com.exo.demo.dao;

import com.exo.demo.model.Role;
import com.exo.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Baladou
 *
 */

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * @param username
     */
    User findByUsername(String username);

    /**
     * @param email
     */
    User findByEmail(String email);

    /**
     *
     * @param role
     * @return
     */
    List<User> findByRoleContaining(Role role);

    /**
     *
     * @param firstname
     * @return
     */
    List<User> findByFirstNameContaining(String firstname);

}
