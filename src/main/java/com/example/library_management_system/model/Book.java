package com.example.library_management_system.model;

import com.example.library_management_system.enums.BookAvailable;
import com.example.library_management_system.enums.BorrowedStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bookName;
    private String authorName;
    private String genre;
    private Integer stock;

    public Book(){

    }

}

