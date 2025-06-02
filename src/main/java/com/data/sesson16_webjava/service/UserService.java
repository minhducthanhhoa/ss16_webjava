package com.data.sesson16_webjava.service;

import com.data.sesson16_webjava.model.User;

public interface UserService {
    boolean register(User user);
    User login(String username, String password);
}
