package com.emp.entities;

import jakarta.persistence.*;

@Entity
public class EmployeeEntity {
    @Id
    private String employeeId;

    @Column(nullable = false)
    private String employeeName;

    @Column(nullable = false,length = 10)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @JoinColumn(name = "reports_to")
    private String reportsTo;

    @Column
    private String profileImage;


    public EmployeeEntity() {
    }

    public EmployeeEntity(String employeeId, String employeeName, String phoneNumber, String email, String reportsTo, String profileImage) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.reportsTo = reportsTo;
        this.profileImage = profileImage;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}

