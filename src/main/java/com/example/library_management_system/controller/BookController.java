package com.example.library_management_system.controller;

import com.example.library_management_system.dto.request.AddBookRequest;
import com.example.library_management_system.dto.request.BorrowBookRequest;
import com.example.library_management_system.dto.response.AddBookResponse;
import com.example.library_management_system.dto.response.AvailableBookResponse;
import com.example.library_management_system.dto.response.BorrowBookResponse;
import com.example.library_management_system.enums.ResponseStatus;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.UserPrincipal;
import com.example.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/api/book/add")
    public AddBookResponse addBook(@RequestBody AddBookRequest bookRequest){
        AddBookResponse response = new AddBookResponse();
        try {
            Book savedBook = bookService.addBook(bookRequest);
            response.setId(savedBook.getId());
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            response.setResponseStatus(ResponseStatus.FAILED);
        }
        return response;
    }


    @GetMapping("/api/book/getBooks")
    public List<AvailableBookResponse> listAvailableBooks(){
        return bookService.listAvailableBooks();
    }

    @PostMapping("/api/book/borrow")
    public BorrowBookResponse borrowBook(Authentication authentication, @RequestBody BorrowBookRequest request) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return bookService.borrowBook(username, request);    }

    @GetMapping("/api/book/search")
    public List<Book> searchBooks(@RequestParam("keyword") String keyword) {
        return bookService.searchBooks(keyword);
    }
}
