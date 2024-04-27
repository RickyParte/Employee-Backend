package com.emp.service;

import com.emp.entities.EmployeeEntity;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public String registerEmployee(EmployeeEntity employeeEntity);

    public List<EmployeeEntity> getAllEmployees();

    public void deleteEmployee(String employeeId);

    public EmployeeEntity updateEmployee(EmployeeEntity employeeEntity);

    public EmployeeEntity getEmployeeByEmail(String email);

    public Optional<EmployeeEntity> getManagerById(String employeeId);

    public Optional<EmployeeEntity> getEmployeeById(String employeeId);

    public List<EmployeeEntity> getAllEmployeesByPagination(Pageable pageable);

}
