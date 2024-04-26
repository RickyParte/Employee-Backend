package com.emp.service;

import com.emp.dao.EmployeeRepository;
import com.emp.entities.EmployeeEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public String registerEmployee(EmployeeEntity employeeEntity){
        EmployeeEntity employeeSavedEntity = employeeRepository.save(employeeEntity);
        if(employeeSavedEntity.getEmployeeId()!=null){
            return employeeSavedEntity.getEmployeeId();
        }
        return "Error in Connecting to DB";
    }

    public List<EmployeeEntity> getAllEmployees(){
        List<EmployeeEntity> allEmployees=employeeRepository.findAll();
        return allEmployees;
    }

    public void deleteEmployee(String employeeId){
        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public EmployeeEntity updateEmployee(EmployeeEntity employeeEntity){
        EmployeeEntity updatedEmployee=employeeRepository.save(employeeEntity);
        return updatedEmployee;
    }

    public EmployeeEntity getEmployeeByEmail(String email){
        EmployeeEntity employeeEntityByEmail=employeeRepository.findByEmail(email);
        if(employeeEntityByEmail!=null){
            return employeeEntityByEmail;
        }
        return null;
    }

    public EmployeeEntity getManager(String employeeId){
        EmployeeEntity employeeEntity=employeeRepository.getOne(employeeId);
        if(employeeEntity!=null){
            return employeeEntity;
        }
        return null;
    }

    public Optional<EmployeeEntity> getManagerById(String employeeId){
        Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(employeeId);
        if(employeeEntity!=null){
            return employeeEntity;
        }
        return null;
    }

    public Optional<EmployeeEntity> getEmployeeById(String employeeId){
        Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(employeeId);
        if(employeeEntity!=null){
            return employeeEntity;
        }
        return null;
    }

}
