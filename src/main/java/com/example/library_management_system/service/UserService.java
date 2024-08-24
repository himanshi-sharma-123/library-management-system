package com.example.library_management_system.service;

import com.example.library_management_system.model.User;
import com.example.library_management_system.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void register(User user) {
        userRepo.save(user);
    }
    
}
