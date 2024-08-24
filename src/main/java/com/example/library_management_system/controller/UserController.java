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

//    public AddBookResponse addBook(@RequestBody AddBookRequest bookRequest){
//        AddBookResponse response = new AddBookResponse();
//        try {
//            Book savedBook = bookService.addBook(bookRequest);
//            response.setId(savedBook.getId());
//            response.setResponseStatus(com.example.library_management_system.enums.ResponseStatus.SUCCESS);
//        }catch (Exception e){
//            e.printStackTrace();
//            response.setResponseStatus(ResponseStatus.FAILED);
//        }
//        return response;
//    }
}
