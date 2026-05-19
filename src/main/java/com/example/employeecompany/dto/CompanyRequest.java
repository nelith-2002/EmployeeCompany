package com.example.employeecompany.dto;

import jakarta.validation.constraints.NotBlank;

public class CompanyRequest {

    @NotBlank(message = "name must not be blank.")
    private String name;

    @NotBlank(message = "industry must not be blank.")
    private String industry;

    @NotBlank(message = "location must not be blank.")
    private String location;

    public String getName() {
        return name;
    }

    public String getIndustry() {
        return industry;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
