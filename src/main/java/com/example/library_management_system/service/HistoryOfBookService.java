package com.example.library_management_system.service;

import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.HistoryOfBook;
import com.example.library_management_system.model.User;
import com.example.library_management_system.repo.BookRepo;
import com.example.library_management_system.repo.HistoryOfBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryOfBookService {

    @Autowired
    private HistoryOfBookRepo historyOfBookRepo;

    @Autowired
    private BookRepo bookRepo;

    public String returnBook(User user, Integer bookId) {
        Optional<Book> bookOptional = bookRepo.findById(bookId);
        if (bookOptional.isEmpty()) {
            return "Book not found";
        }

        Book book = bookOptional.get();
        List<HistoryOfBook> borrowRecords = historyOfBookRepo.findByUserAndBookAndReturnDateIsNull(user, book);

        if (borrowRecords.isEmpty()) {
            return "This book is not currently borrowed by you.";
        }

        HistoryOfBook borrowHistory = borrowRecords.get(0);
        borrowHistory.setReturnDate(new Date());
        historyOfBookRepo.save(borrowHistory);

        book.setStock(book.getStock() + 1);
        bookRepo.save(book);

        return "Book returned successfully";
    }
}
