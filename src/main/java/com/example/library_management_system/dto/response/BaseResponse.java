package com.example.library_management_system.dto.response;

import com.example.library_management_system.enums.ResponseStatus;
import lombok.Data;

@Data
public class BaseResponse {

    ResponseStatus responseStatus;
}
