package com.example.graphql_demo.mapper;

import com.example.graphql_demo.dto.UserDTO;
import com.example.graphql_demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO dto);
}