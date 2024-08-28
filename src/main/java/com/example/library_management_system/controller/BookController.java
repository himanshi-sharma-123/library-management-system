package com.example.library_management_system.controller;

import com.example.library_management_system.dto.request.AddBookRequest;
import com.example.library_management_system.dto.request.BorrowBookRequest;
import com.example.library_management_system.dto.response.AddBookResponse;
import com.example.library_management_system.dto.response.AvailableBookResponse;
import com.example.library_management_system.dto.response.BorrowBookResponse;
import com.example.library_management_system.enums.ResponseStatus;
import com.example.library_management_system.enums.UserType;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.UserPrincipal;
import com.example.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public AddBookResponse addBook(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody AddBookRequest bookRequest){
        AddBookResponse response = new AddBookResponse();

        if (userPrincipal.getUserType() != UserType.ADMIN) {
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setMessage("You are not authorized to perform this action.");
            return response;
        }
        try {
            Book savedBook = bookService.addBook(bookRequest);
            response.setId(savedBook.getId());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setMessage("Book added successfully.");
        }catch (Exception e){
            e.printStackTrace();
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setMessage("Failed to add book.");
        }
        return response;
    }


    @GetMapping("/api/books")
    public List<AvailableBookResponse> listAvailableBooks(){
        return bookService.listAvailableBooks();
    }

    @PostMapping("/api/books/borrow")
    public ResponseEntity<BorrowBookResponse> borrowBook(Authentication authentication, @RequestBody BorrowBookRequest request) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        try {
            BorrowBookResponse response = bookService.borrowBook(username, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            BorrowBookResponse response = new BorrowBookResponse();
            response.setMessage("Unexpected error occurred");
            response.setResponseStatus(ResponseStatus.FAILED);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
//        return bookService.borrowBook(username, request);
    }

//    @GetMapping("/api/books/search")
//    public List<Book> searchBooks(@RequestParam("keyword") String keyword) {
//        return bookService.searchBooks(keyword);
//    }

    @GetMapping("/api/books/search")
    public List<Book> searchBooks(@RequestParam(value = "genre", required = false) String genre, @RequestParam(value = "authorName", required = false) String authorName, @RequestParam(value = "bookName", required = false) String bookName) {
        return bookService.searchBooks(genre, authorName, bookName);
    }
}
