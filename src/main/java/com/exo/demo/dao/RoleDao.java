package com.exo.demo.dao;

import com.exo.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 * @author Baladou
 */

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    /**
     * @param name
     */
    Role findByName(String name);

}