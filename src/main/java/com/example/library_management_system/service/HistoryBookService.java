package com.example.library_management_system.service;

import com.example.library_management_system.model.HistoryOfBook;
import com.example.library_management_system.repo.HistoryBooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryBookService {

    @Autowired
    private HistoryBooksRepo historyBooksRepo;

    public void postBorrowHistory(HistoryOfBook historyOfBook) {
        historyBooksRepo.save(historyOfBook);
    }


    public List<HistoryOfBook> getBorrowHistory() {
        return historyBooksRepo.findAll();
    }
}
