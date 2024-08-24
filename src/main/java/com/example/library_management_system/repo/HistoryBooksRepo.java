package com.example.library_management_system.repo;

import com.example.library_management_system.model.HistoryOfBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryBooksRepo extends JpaRepository<HistoryOfBook, Integer> {
}
