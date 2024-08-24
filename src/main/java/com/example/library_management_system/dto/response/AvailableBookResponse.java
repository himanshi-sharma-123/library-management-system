package com.example.library_management_system.dto.response;

import lombok.Data;

@Data
public class AvailableBookResponse {
    private Integer id;
    private String bookName;
    private String authorName;
    private String genre;
    private Integer stock;
}
