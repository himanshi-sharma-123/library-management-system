package com.example.library_management_system.controller;

import com.example.library_management_system.model.User;
import com.example.library_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/register")
    public void register(@RequestBody User user){
        userService.register(user);
    }


}
