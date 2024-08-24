package com.example.library_management_system.dto.request;

import com.example.library_management_system.enums.UserType;
import com.example.library_management_system.model.HistoryOfBook;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class AddUserRequest {
    private String username;
    private String password;
    private String email;

    private UserType userType;

    @OneToMany(mappedBy = "user")
    private List<HistoryOfBook> historyOfBooks;
}
