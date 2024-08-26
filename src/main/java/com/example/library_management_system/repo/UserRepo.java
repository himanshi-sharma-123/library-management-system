package com.example.library_management_system.repo;

import com.example.library_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Optional<User> findByEmailOrUsername(String email, String username);
}
