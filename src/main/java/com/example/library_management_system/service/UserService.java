package com.example.library_management_system.service;

import com.example.library_management_system.dto.request.AddBookRequest;
import com.example.library_management_system.dto.request.AddUserRequest;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.User;
import com.example.library_management_system.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public User register(AddUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setUserType(request.getUserType());
        return userRepo.save(user);
    }

//    public Book addBook(AddBookRequest request) {
//        Book book = new Book();
//        book.setBookName(request.getBookName());
//        book.setAuthorName(request.getAuthorName());
//        book.setGenre(request.getGenre());
//        book.setStock(request.getStock());
//
//        return bookRepo.save(book);
//    }

}
