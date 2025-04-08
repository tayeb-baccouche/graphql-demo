package com.example.graphql_demo.resolver;

import com.example.graphql_demo.entity.User;
import com.example.graphql_demo.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserMutation {

    private final UserRepository userRepository;

    public UserMutation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @MutationMapping
    public User createUser(@Argument String name, @Argument String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        userRepository.deleteById(id);
        return true;
    }
}