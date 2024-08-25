package com.example.library_management_system.controller;

import com.example.library_management_system.model.HistoryOfBook;
import com.example.library_management_system.model.User;
import com.example.library_management_system.model.UserPrincipal;
import com.example.library_management_system.service.BookService;
import com.example.library_management_system.service.HistoryOfBookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String returnBook(@AuthenticationPrincipal User user, @RequestParam("bookId") Integer bookId) {
        return historyOfBookService.returnBook(user, bookId);
    }


    @GetMapping("/api/borrow/history")
    public List<HistoryOfBook> getBorrowHistory(@AuthenticationPrincipal User user) {
        return historyOfBookService.getBorrowHistory(user);
    }
}
