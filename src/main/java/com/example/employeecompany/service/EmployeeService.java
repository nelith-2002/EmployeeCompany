package com.example.employeecompany.service;

import com.example.employeecompany.dto.EmployeeRequest;
import com.example.employeecompany.dto.EmployeeResponse;
import com.example.employeecompany.entity.Company;
import com.example.employeecompany.entity.Employee;
import com.example.employeecompany.exception.ResourceNotFoundException;
import com.example.employeecompany.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final String BASE_URL = "http://localhost:8080/api/v1/companies";

    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;

    public EmployeeService(EmployeeRepository employeeRepository, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }

    public List<EmployeeResponse> getAllEmployees(Long companyId) {
        companyService.findCompanyEntityById(companyId);

        return employeeRepository.findByCompanyId(companyId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public EmployeeResponse getEmployeeById(Long companyId, Long employeeId) {
        Employee employee = findEmployeeEntityByIdAndCompanyId(companyId, employeeId);
        return mapToResponse(employee);
    }

    public EmployeeResponse createEmployee(Long companyId, EmployeeRequest request) {
        Company company = companyService.findCompanyEntityById(companyId);

        Employee employee = new Employee(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getJobTitle(),
                company
        );

        Employee savedEmployee = employeeRepository.save(employee);
        return mapToResponse(savedEmployee);
    }

    public EmployeeResponse updateEmployee(Long companyId, Long employeeId, EmployeeRequest request) {
        Employee employee = findEmployeeEntityByIdAndCompanyId(companyId, employeeId);

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setJobTitle(request.getJobTitle());

        Employee updatedEmployee = employeeRepository.save(employee);
        return mapToResponse(updatedEmployee);
    }

    public void deleteEmployee(Long companyId, Long employeeId) {
        Employee employee = findEmployeeEntityByIdAndCompanyId(companyId, employeeId);
        employeeRepository.delete(employee);
    }

    private Employee findEmployeeEntityByIdAndCompanyId(Long companyId, Long employeeId) {
        companyService.findCompanyEntityById(companyId);

        return employeeRepository.findByIdAndCompanyId(employeeId, companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "EMPLOYEE_NOT_FOUND",
                        "Employee with id " + employeeId + " was not found."
                ));
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        Long companyId = employee.getCompany().getId();

        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getJobTitle(),
                companyId,
                BASE_URL + "/" + companyId + "/employees/" + employee.getId()
        );
    }
}