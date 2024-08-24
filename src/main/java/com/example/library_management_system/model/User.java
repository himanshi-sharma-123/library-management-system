package com.example.library_management_system.model;

import com.example.library_management_system.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String email;

    private UserType userType;

    @OneToMany(mappedBy = "user")
    private List<HistoryOfBook> historyOfBooks;

}
