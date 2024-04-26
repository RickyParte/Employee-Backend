package com.emp.controllers;

import com.emp.entities.EmployeeEntity;
import com.emp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("employee")
public class EmployeeEndPoint {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("add-employee")
    public ResponseEntity addEmployee(@RequestBody EmployeeEntity employeeEntity){
        try{

            String employeeId= UUID.randomUUID().toString();
            employeeEntity.setEmployeeId(employeeId);

            String registerMessage = employeeService.registerEmployee(employeeEntity);

            if(registerMessage.equals("Error in Connecting to DB")){
                return ResponseEntity.ok(registerMessage);
            }

            return ResponseEntity.ok(registerMessage);
        }
        catch(Exception e) {
            System.out.println("Exception caught in addEmployee API");
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body("Something Went Wrong!!!");
        }
    }

    @GetMapping("get-employees")
    public ResponseEntity<List<EmployeeEntity>> getAllEmployee(){
        List<EmployeeEntity> employees;
        try{
            employees = employeeService.getAllEmployees();
        }
        catch (Exception e){
            System.out.println("Exception caught in getEmployee API");
            throw e;
        }
        return ResponseEntity.ok(employees);
    }

    @PostMapping("delete-employee-by-id")
    public String deleteEmployeeByEmployeeId(@RequestHeader String employeeId){
        try{
            employeeService.deleteEmployee(employeeId);
            return "Employee Deleted!!!";
        }
        catch (Exception e){
            System.out.println("Exception caught in deleteEmployee API");
            System.out.println(e.getMessage());
        }
        return "Something Wrong!!";
    }

    @PostMapping("update-employee/{employeeId}")
    public ResponseEntity updateEmployee(@PathVariable String employeeId,@RequestBody EmployeeEntity employeeEntity){
        try{
            employeeEntity.setEmployeeId(employeeId);
            employeeService.updateEmployee(employeeEntity);
            return ResponseEntity.ok("User Updated!!!");
        }
        catch(Exception e) {
            System.out.println("Exception caught in updateEmployee API");
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body("Something Went Wrong!!!");
        }
    }

    @PostMapping("get-nth-manager")
    public ResponseEntity getNthManager(@RequestHeader String employeeId,@RequestHeader String managerLevel){

        try{
            int nLevel=Integer.parseInt(managerLevel);
            String managerId=employeeService.getEmployeeById(employeeId).get().getReportsTo();
//            System.out.println(managerId);

            for(int i=1;i<nLevel;i++){
                EmployeeEntity getManager=employeeService.getManagerById(managerId).get();
                if(getManager==null){
                    return ResponseEntity.ok("Not This Level Manager Available there...");
                }
                managerId=getManager.getReportsTo();
            }
            EmployeeEntity managerEntity=employeeService.getManagerById(managerId).get();
            return ResponseEntity.ok(managerEntity);
        }
        catch (Exception e) {
            System.out.println("Exception in MangerApi");
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body("No Manager Found At this Level");
        }
    }
}
