package com.example.employeecompany.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmployeeRequest {

    @NotBlank(message = "firstName must not be blank.")
    private String firstName;

    @NotBlank(message = "lastName must not be blank.")
    private String lastName;

    @NotBlank(message = "email must not be blank.")
    @Email(message = "email must be a valid email address.")
    private String email;

    @NotBlank(message = "jobTitle must not be blank.")
    private String jobTitle;

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}