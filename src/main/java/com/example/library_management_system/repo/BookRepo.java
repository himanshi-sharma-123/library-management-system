package com.example.library_management_system.repo;

import com.example.library_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    List<Book> findByStockGreaterThan(Integer stock);

//    List<Book> findByBookName(String bookName);

    List<Book> findByBookNameOrAuthorNameOrGenre(String bookName, String authorName, String genre);


}
