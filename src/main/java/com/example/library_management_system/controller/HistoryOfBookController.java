package com.example.library_management_system.controller;

import com.example.library_management_system.model.HistoryOfBook;
import com.example.library_management_system.model.User;
import com.example.library_management_system.model.UserPrincipal;
import com.example.library_management_system.service.BookService;
import com.example.library_management_system.service.HistoryOfBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryOfBookController {

    @Autowired
    private HistoryOfBookService historyOfBookService;

    @Autowired
    private BookService bookService;


    @PostMapping("/api/book/return")
    public ResponseEntity<String> returnBook(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam("bookId") Integer bookId) {
        User user = userPrincipal.getUser();
        String result = historyOfBookService.returnBook(user, bookId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/borrow/history")
    public List<HistoryOfBook> getBorrowHistory(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        User user = userPrincipal.getUser();
        return historyOfBookService.getBorrowHistory(user);
    }
}
