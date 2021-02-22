package com.exo.demo.mapper;

import com.exo.demo.dao.RoleDao;
import com.exo.demo.dto.RoleDto;
import com.exo.demo.dto.UserDto;
import com.exo.demo.model.Role;
import com.exo.demo.model.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.Converter;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {


    UserDto toUserDto(User user);

    User toUser(UserDto user);


}