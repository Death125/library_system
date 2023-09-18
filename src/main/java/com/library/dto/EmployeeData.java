package com.library.dto;

import jakarta.validation.constraints.NotEmpty;

public class EmployeeData {

    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String employee_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

}
