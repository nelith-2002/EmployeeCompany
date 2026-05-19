package com.example.employeecompany.dto;

import java.util.List;

public class ApiError {

    private String code;
    private String message;
    private List<FieldValidationError> errors;

    public ApiError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiError(String code, String message, List<FieldValidationError> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<FieldValidationError> getErrors() {
        return errors;
    }
}
