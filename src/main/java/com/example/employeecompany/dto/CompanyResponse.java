package com.example.employeecompany.dto;

public class CompanyResponse {

    private Long id;
    private String name;
    private String industry;
    private String location;
    private String resourceURL;

    public CompanyResponse(Long id, String name, String industry, String location, String resourceURL) {
        this.id = id;
        this.name = name;
        this.industry = industry;
        this.location = location;
        this.resourceURL = resourceURL;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIndustry() {
        return industry;
    }

    public String getLocation() {
        return location;
    }

    public String getResourceURL() {
        return resourceURL;
    }
}
