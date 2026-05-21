package com.example.employeecompany.controller;

import com.example.employeecompany.dto.ApiResponse;
import com.example.employeecompany.dto.EmployeeRequest;
import com.example.employeecompany.dto.EmployeeResponse;
import com.example.employeecompany.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAllEmployees(
            @PathVariable Long companyId
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(employeeService.getAllEmployees(companyId))
        );
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployeeById(
            @PathVariable Long companyId,
            @PathVariable Long employeeId
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(employeeService.getEmployeeById(companyId, employeeId))
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(
            @PathVariable Long companyId,
            @Valid @RequestBody EmployeeRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(employeeService.createEmployee(companyId, request)));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployee(
            @PathVariable Long companyId,
            @PathVariable Long employeeId,
            @Valid @RequestBody EmployeeRequest request
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(employeeService.updateEmployee(companyId, employeeId, request))
        );
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long companyId,
            @PathVariable Long employeeId
    ) {
        employeeService.deleteEmployee(companyId, employeeId);
        return ResponseEntity.noContent().build();
    }
}
