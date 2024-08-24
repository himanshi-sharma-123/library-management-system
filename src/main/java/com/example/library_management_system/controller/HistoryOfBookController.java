package com.example.library_management_system.controller;

import com.example.library_management_system.model.User;
import com.example.library_management_system.service.HistoryOfBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryOfBookController {

    @Autowired
    private HistoryOfBookService historyOfBookService;

    @PostMapping("/api/book/return")
    public String returnBook(@AuthenticationPrincipal User user, @RequestParam("bookId") Integer bookId) {
        return historyOfBookService.returnBook(user, bookId);
    }
}
