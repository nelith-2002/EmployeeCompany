package com.example.employeecompany.service;

import com.example.employeecompany.dto.CompanyRequest;
import com.example.employeecompany.dto.CompanyResponse;
import com.example.employeecompany.entity.Company;
import com.example.employeecompany.exception.ResourceNotFoundException;
import com.example.employeecompany.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private static final String BASE_URL = "http://localhost:8080/api/v1/companies";

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CompanyResponse getCompanyById(Long companyId) {
        Company company = findCompanyEntityById(companyId);
        return mapToResponse(company);
    }

    public CompanyResponse createCompany(CompanyRequest request) {
        Company company = new Company(
                request.getName(),
                request.getIndustry(),
                request.getLocation()
        );

        Company savedCompany = companyRepository.save(company);
        return mapToResponse(savedCompany);
    }

    public CompanyResponse updateCompany(Long companyId, CompanyRequest request) {
        Company company = findCompanyEntityById(companyId);

        company.setName(request.getName());
        company.setIndustry(request.getIndustry());
        company.setLocation(request.getLocation());

        Company updatedCompany = companyRepository.save(company);
        return mapToResponse(updatedCompany);
    }

    public void deleteCompany(Long companyId) {
        Company company = findCompanyEntityById(companyId);
        companyRepository.delete(company);
    }

    public Company findCompanyEntityById(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "COMPANY_NOT_FOUND",
                        "Company with id " + companyId + " was not found."
                ));
    }

    private CompanyResponse mapToResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getIndustry(),
                company.getLocation(),
                BASE_URL + "/" + company.getId()
        );
    }
}
