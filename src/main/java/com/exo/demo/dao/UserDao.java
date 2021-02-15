package com.exo.demo.dao;

import com.exo.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
}
