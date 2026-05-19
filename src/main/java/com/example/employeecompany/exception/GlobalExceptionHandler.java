package com.example.employeecompany.exception;

import com.example.employeecompany.dto.ApiError;
import com.example.employeecompany.dto.ApiResponse;
import com.example.employeecompany.dto.FieldValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiError error = new ApiError(ex.getErrorCode(), ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationError(MethodArgumentNotValidException ex) {
        List<FieldValidationError> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldValidationError(error.getField(), error.getDefaultMessage()))
                .toList();

        ApiError apiError = new ApiError(
                "VALIDATION_ERROR",
                "Request body contains invalid fields.",
                validationErrors
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(apiError));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralError(Exception ex) {
        ApiError error = new ApiError(
                "INTERNAL_SERVER_ERROR",
                "Unexpected server failure."
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(error));
    }
}
