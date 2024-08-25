package com.example.library_management_system.controller;

import com.example.library_management_system.dto.request.AddBookRequest;
import com.example.library_management_system.dto.request.AddUserRequest;
import com.example.library_management_system.dto.response.AddBookResponse;
import com.example.library_management_system.dto.response.AddUserResponse;
import com.example.library_management_system.enums.ResponseStatus;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.User;
import com.example.library_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/register")
    public AddUserResponse register(@RequestBody AddUserRequest userRequest){
        AddUserResponse response = new AddUserResponse();
        try {
            User registerUser = userService.register(userRequest);
            response.setId(registerUser.getId());
            response.setResponseStatus(com.example.library_management_system.enums.ResponseStatus.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            response.setResponseStatus(ResponseStatus.FAILED);
        }
        return response;
    }

    @PostMapping("/api/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }

    @GetMapping("/api/getUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
