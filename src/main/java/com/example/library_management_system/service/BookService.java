package com.example.library_management_system.service;

import com.example.library_management_system.dto.request.AddBookRequest;
import com.example.library_management_system.dto.request.BorrowBookRequest;
import com.example.library_management_system.dto.response.AvailableBookResponse;
import com.example.library_management_system.dto.response.BorrowBookResponse;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.HistoryOfBook;
import com.example.library_management_system.model.User;
import com.example.library_management_system.repo.BookRepo;
import com.example.library_management_system.repo.HistoryOfBookRepo;
import com.example.library_management_system.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private HistoryOfBookRepo historyOfBookRepo;

    public Book addBook(AddBookRequest request) {
        Book book = new Book();
        book.setBookName(request.getBookName());
        book.setAuthorName(request.getAuthorName());
        book.setGenre(request.getGenre());
        book.setStock(request.getStock());

        return bookRepo.save(book);
    }

    public BorrowBookResponse borrowBook(String username, BorrowBookRequest request) {
        BorrowBookResponse response = new BorrowBookResponse();

        Book book = bookRepo.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getStock() <= 0) {
            response.setMessage("Book is not available");
            return response;
        }

        User user = userRepo.findByUsername(username);


        book.setStock(book.getStock() - 1);
        bookRepo.save(book);

        HistoryOfBook history = new HistoryOfBook();
        history.setUser(user);
        history.setBook(book);
        history.setBorrowedDate(new Date());
        historyOfBookRepo.save(history);

        response.setMessage("Book borrowed successfully");
        return response;
    }


    public List<AvailableBookResponse> listAvailableBooks() {
        List<Book> availableBooks = bookRepo.findByStockGreaterThan(0);
        return availableBooks.stream().map(book -> {
            AvailableBookResponse response = new AvailableBookResponse();
            response.setId(book.getId());
            response.setBookName(book.getBookName());
            response.setAuthorName(book.getAuthorName());
            response.setGenre(book.getGenre());
            response.setStock(book.getStock());
            return response;
        }).collect(Collectors.toList());
    }

    public List<Book> searchBooks(String keyword) {
        return bookRepo.findByBookNameOrAuthorNameOrGenre(keyword, keyword, keyword);
    }

    public List<HistoryOfBook> getBorrowHistory(User user) {
        return historyOfBookRepo.findByUser(user);
    }
}
