package com.exo.demo.mapper;

import com.exo.demo.dto.RoleDto;
import com.exo.demo.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RoleMapper {


    RoleDto toRoleDto(Role role);

    Role toRole(RoleDto role);


}