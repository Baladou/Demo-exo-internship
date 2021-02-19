package com.exo.demo.dao;

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
     * @param firstname
     * @return
     */
    List<User> findByFirstNameLike(String firstname);
    List<User> findByFirstNameContaining(String firstname);

}
