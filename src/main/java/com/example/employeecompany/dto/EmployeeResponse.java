package com.example.employeecompany.dto;

public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private Long companyId;
    private String resourceURL;

    public EmployeeResponse(
            Long id,
            String firstName,
            String lastName,
            String email,
            String jobTitle,
            Long companyId,
            String resourceURL
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jobTitle = jobTitle;
        this.companyId = companyId;
        this.resourceURL = resourceURL;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getResourceURL() {
        return resourceURL;
    }
}