package com.example.graphql_demo.resolver;

import com.example.graphql_demo.dto.UpdateUserInput;
import com.example.graphql_demo.dto.UserDTO;
import com.example.graphql_demo.dto.UserInput;
import com.example.graphql_demo.entity.User;
import com.example.graphql_demo.mapper.UserMapper;
import com.example.graphql_demo.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserMutation {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserMutation(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @MutationMapping
    public UserDTO createUser(@Argument("input") UserInput input) {
        User user = new User();
        user.setName(input.getName());
        user.setEmail(input.getEmail());
        return userMapper.toDto(userRepository.save(user));
    }

    @MutationMapping
    public UserDTO updateUser(@Argument("input") UpdateUserInput input) {
        return userRepository.findById(input.getId()).map(user -> {
            if (input.getName() != null) user.setName(input.getName());
            if (input.getEmail() != null) user.setEmail(input.getEmail());
            return userMapper.toDto(userRepository.save(user));
        }).orElse(null);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        userRepository.deleteById(id);
        return true;
    }
}
