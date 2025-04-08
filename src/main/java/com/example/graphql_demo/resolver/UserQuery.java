package com.example.graphql_demo.resolver;

import com.example.graphql_demo.dto.UserDTO;
import com.example.graphql_demo.mapper.UserMapper;
import com.example.graphql_demo.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserQuery {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserQuery(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @QueryMapping
    public List<UserDTO> users() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @QueryMapping
    public UserDTO user(@Argument Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElse(null);
    }
}