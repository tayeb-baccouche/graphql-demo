package com.example.graphql_demo.resolver;

import com.example.graphql_demo.entity.User;
import com.example.graphql_demo.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserQuery {

    private final UserRepository userRepository;

    public UserQuery(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryMapping
    public List<User> users() {
        return userRepository.findAll();
    }

    @QueryMapping
    public User user(@Argument Long id) {
        return userRepository.findById(id).orElse(null);
    }
}