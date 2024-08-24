package com.example.library_management_system.dto.request;

import lombok.Data;

@Data
public class AddBookRequest {

    private String bookName;
    private String authorName;
    private String genre;
    private Integer stock;
}
