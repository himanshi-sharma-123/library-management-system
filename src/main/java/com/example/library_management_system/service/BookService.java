package com.example.library_management_system.service;

import com.example.library_management_system.dto.request.AddBookRequest;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public Book addBook(AddBookRequest request) {
        Book book = new Book();
        book.setBookName(request.getBookName());
        book.setAuthorName(request.getAuthorName());
        book.setGenre(request.getGenre());
        book.setStock(request.getStock());

        return bookRepo.save(book);
    }

    public List<Book> getBooks() {
        return bookRepo.findAll();
    }
}
