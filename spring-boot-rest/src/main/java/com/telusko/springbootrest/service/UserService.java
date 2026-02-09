package com.telusko.springbootrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.telusko.springbootrest.model.User;
import com.telusko.springbootrest.repo.UserRepo;

@Service
public class UserService {

    private final UserRepo repo;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepo repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
}
