package com.data.sesson16_webjava.service;

import com.data.sesson16_webjava.model.User;
import com.data.sesson16_webjava.repository.UserRepository;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository = new UserRepository();

    @Override
    public boolean register(User user) {
        user.setRole("USER");
        user.setStatus(true);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
