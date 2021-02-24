package com.exo.demo.mapper;

import com.exo.demo.dto.UserDto;
import com.exo.demo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {


    UserDto toUserDto(User user);

    User toUser(UserDto user);


}