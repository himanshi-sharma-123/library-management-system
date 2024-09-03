package com.example.library_management_system.controller;

import com.example.library_management_system.dto.request.AddBookRequest;
import com.example.library_management_system.dto.request.AddUserRequest;
import com.example.library_management_system.dto.response.AddBookResponse;
import com.example.library_management_system.dto.response.AddUserResponse;
import com.example.library_management_system.enums.ResponseStatus;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.User;
import com.example.library_management_system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/api/register")
    public AddUserResponse register(@RequestBody AddUserRequest userRequest){
        AddUserResponse response = new AddUserResponse();
        logger.info("Add User Request : " + userRequest);
        try {
            Optional<User> existingUser = userService.findByEmailOrUsername(userRequest.getEmail(), userRequest.getUsername());

            if(existingUser.isPresent()){
                response.setResponseStatus(ResponseStatus.FAILED);
                response.setMessage("The User is already exist with the given username and password");
            }else {
                User registerUser = userService.register(userRequest);
                System.out.println(registerUser);
                response.setId(registerUser.getId());
                response.setResponseStatus(ResponseStatus.SUCCESS);
                response.setMessage("User registered successfully.");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setMessage("An error occurred during registration.");

        }
        logger.info("Add User Response : " + response);
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
