package com.emp.dto;

import com.emp.entities.EmployeeEntity;

public class EmployeeResponse {
    private String employeeId;
    private String message;

    private EmployeeEntity employeeManagerEntity;

    public EmployeeResponse() {}


    public EmployeeResponse(EmployeeEntity employeeManagerEntity) {
        this.employeeManagerEntity = employeeManagerEntity;
    }

    public EmployeeResponse(String employeeId, String message, EmployeeEntity employeeManagerEntity) {
        this.employeeId = employeeId;
        this.message = message;
        this.employeeManagerEntity = employeeManagerEntity;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeManagerEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeManagerEntity) {
        this.employeeManagerEntity = employeeManagerEntity;
    }
}
