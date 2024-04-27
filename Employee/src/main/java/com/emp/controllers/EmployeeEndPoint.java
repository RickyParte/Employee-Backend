package com.emp.controllers;

import com.emp.entities.EmployeeEntity;
import com.emp.service.EmailService;
import com.emp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employee")
public class EmployeeEndPoint {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmailService emailService;

    @PostMapping("add-employee")
    public ResponseEntity addEmployee(@RequestBody EmployeeEntity employeeEntity){
        try{

            String employeeId= UUID.randomUUID().toString();
            employeeEntity.setEmployeeId(employeeId);

            EmployeeEntity getExistingEmailEmployee=employeeService.getEmployeeByEmail(employeeEntity.getEmail());
            if(getExistingEmailEmployee==null){
                String registerMessage = employeeService.registerEmployee(employeeEntity);

                if(registerMessage.equals("Error in Connecting to DB")){
                    return ResponseEntity.ok(registerMessage);
                }

                try{
                    String managerEmail=employeeService.getManagerById(employeeEntity.getReportsTo()).get().getEmail();
                    String subject="New Employee Reporting You!!";
                    String emailContent=employeeEntity.getEmployeeName() + " will now work under you. Mobile number is "
                            + employeeEntity.getPhoneNumber() + " and email is " + employeeEntity.getEmail();
                    emailService.sendEmailNotificationToManager1(managerEmail,subject,emailContent);
                }
                catch(Exception e){
                    System.out.println("API Exception Email Sending");
                    System.out.println(e.getMessage());
                }

                return ResponseEntity.ok(registerMessage);
            }
            else{
                return ResponseEntity.ok("Employee Email is Already Registeres!!!!");
            }


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

    @GetMapping("/get-all-employees")
    public ResponseEntity getEmployeeWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "employeeName") String sortBy) {
        try {
            List<EmployeeEntity> employees = employeeService.getAllEmployeesByPagination(PageRequest.of(page, size, Sort.by(sortBy)));
            if(employees.isEmpty()){
                return new ResponseEntity<>("No Employess Found", HttpStatus.OK);
            }
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve employees: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
