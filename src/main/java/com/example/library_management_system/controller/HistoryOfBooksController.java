package com.example.library_management_system.controller;

import com.example.library_management_system.model.HistoryOfBook;
import com.example.library_management_system.service.HistoryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryOfBooksController {

    @Autowired
    private HistoryBookService historyBookService;

    @PostMapping("/api/borrow/history")
    public void postBorrowHistory(@RequestBody HistoryOfBook historyOfBook){
        historyBookService.postBorrowHistory(historyOfBook);
    }

    @GetMapping("/api/borrow/history")
    public List<HistoryOfBook> getBorrowHistory(){
        return historyBookService.getBorrowHistory();
    }
}
