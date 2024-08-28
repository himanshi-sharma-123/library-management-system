package com.example.library_management_system.dto.response;

import lombok.Data;

@Data
public class BorrowBookResponse extends BaseResponse{
    private String message;
    private String bookName;
}
