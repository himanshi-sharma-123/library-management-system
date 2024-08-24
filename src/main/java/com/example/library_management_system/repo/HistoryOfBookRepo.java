package com.example.library_management_system.repo;

import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.HistoryOfBook;
import com.example.library_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryOfBookRepo extends JpaRepository<HistoryOfBook, Integer> {
    List<HistoryOfBook> findByUserAndBookAndReturnDateIsNull(User user, Book book);
}
