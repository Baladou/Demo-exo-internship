package com.exo.demo.dao;

import com.exo.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
