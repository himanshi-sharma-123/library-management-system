package com.example.library_management_system.repo;

import com.example.library_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    List<Book> findByStockGreaterThan(Integer stock);

//    List<Book> findByBookName(String bookName);

//    List<Book> findByBookNameOrAuthorNameOrGenre(String bookName, String authorName, String genre);

    List<Book> findByBookNameAndAuthorNameAndGenre(String bookName, String authorName, String genre);

    List<Book> findByAuthorNameAndGenre(String authorName, String genre);

    List<Book> findByBookNameAndAuthorName(String bookName, String authorName);

    List<Book> findByGenre(String genre);

    List<Book> findByAuthorName(String authorName);

    List<Book> findByBookName(String bookName);

    List<Book> findByBookNameAndGenre(String bookName, String genre);


}
