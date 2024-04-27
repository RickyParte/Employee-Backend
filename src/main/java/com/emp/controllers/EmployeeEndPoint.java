package com.emp.controllers;

import com.emp.dto.EmployeeResponse;
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

    /**
     * API endpoint to add an employee to the database.
     *
     * @param employeeEntity The employee details to be added.
     * @return ResponseEntity The response indicating the success or failure of the operation.
     */
    @PostMapping("add-employee")
    public ResponseEntity addEmployee(@RequestBody EmployeeEntity employeeEntity){
        try{

            String employeeId= UUID.randomUUID().toString();
            employeeEntity.setEmployeeId(employeeId);

            EmployeeEntity getExistingEmailEmployee=employeeService.getEmployeeByEmail(employeeEntity.getEmail());
            if(getExistingEmailEmployee==null){

                boolean existingManager=false;

                if(employeeEntity.getReportsTo().equals("null")){
                    existingManager=true;
                }
                else{
                    existingManager=employeeService.getManagerById(employeeEntity.getReportsTo()).isPresent();
                }

                if(existingManager){
                    String registerMessage = employeeService.registerEmployee(employeeEntity);

                    if(registerMessage.equals("Error in Connecting to DB")){
                        return ResponseEntity.badRequest().body(new EmployeeResponse(null,"Error in Connecting to DB",null));
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

                    return ResponseEntity.ok(new EmployeeResponse(employeeId, registerMessage,null));
                }
                else{
                    return ResponseEntity.ok(new EmployeeResponse(null, "This EmployeeId "+ employeeEntity.getReportsTo() +" is not associated with any Employee!!",null));
                }
            }
            else{
                return ResponseEntity.badRequest().body(new EmployeeResponse(null, "Employee Email is Already Registered!!!",null));
            }


        }
        catch(Exception e) {
            System.out.println("Exception caught in addEmployee API");
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(new EmployeeResponse(null, "Something Went Wrong!!!",null));
        }
    }

    /**
     * API endpoint to retrieve all employees from the database.
     *
     * @return ResponseEntity<List<EmployeeEntity>> The list of all employees.
     */
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

    /**
     * API endpoint to delete an employee from the database based on their ID.
     *
     * @param employeeId The ID of the employee to be deleted.
     * @return ResponseEntity The response indicating the success or failure of the operation.
     */
    @PostMapping("delete-employee-by-id")
    public ResponseEntity deleteEmployeeByEmployeeId(@RequestHeader String employeeId){
        try{
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.ok().body(new EmployeeResponse(employeeId,"Employee Deleted!!!",null));
        }
        catch (Exception e){
            System.out.println("Exception caught in deleteEmployee API");
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(new EmployeeResponse(null,"Something Went Wrong! Please try again!!!",null));
        }
    }

    /**
     * API endpoint to update the details of an employee in the database based on their ID.
     *
     * @param employeeId The ID of the employee to be updated.
     * @param employeeEntity The updated employee details.
     * @return ResponseEntity<String> The response indicating the success or failure of the operation.
     */
    @PostMapping("update-employee/{employeeId}")
    public ResponseEntity updateEmployee(@PathVariable String employeeId,@RequestBody EmployeeEntity employeeEntity){
        try{
            employeeEntity.setEmployeeId(employeeId);
            employeeService.updateEmployee(employeeEntity);
            return ResponseEntity.ok().body(new EmployeeResponse(employeeId,"Employee Updated SuccessFully!!!",null));
        }
        catch(Exception e) {
            System.out.println("Exception caught in updateEmployee API");
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(new EmployeeResponse(null,"Something Went Wrong! Please try again!!!",null));
        }
    }

    /**
     * API endpoint to get the nth level manager of an employee.
     *
     * @param employeeId    The ID of the employee.
     * @param managerLevel  The level of the manager to retrieve.
     * @return ResponseEntity<String> The response indicating the manager at the specified level.
     */
    @PostMapping("get-nth-manager")
    public ResponseEntity getNthManager(@RequestHeader String employeeId,@RequestHeader String managerLevel){

        try{
            int nLevel=Integer.parseInt(managerLevel);
            String managerId="";
            try {
                managerId=employeeService.getEmployeeById(employeeId).get().getReportsTo();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

//            System.out.println(managerId);

            for(int i=1;i<nLevel;i++){
                EmployeeEntity getManager=null;
                try {
                    boolean checkNull=employeeService.getManagerById(managerId).isPresent();
                    if(!checkNull){
                        return ResponseEntity.ok().body(new EmployeeResponse(null,"Employee Not Found at this level!!! try with less level",null));
                    }
                    getManager=employeeService.getManagerById(managerId).get();
                    managerId=getManager.getReportsTo();
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            EmployeeEntity managerEntity=null;
            try {
                managerEntity=employeeService.getManagerById(managerId).get();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

            if(managerEntity==null){
                return ResponseEntity.ok().body(new EmployeeResponse(null,"Employee Not Found at this level!!! try with less level",managerEntity));
            }
            return ResponseEntity.ok().body(new EmployeeResponse(managerEntity));
        }
        catch (Exception e) {
            System.out.println("Exception in MangerApi");
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(new EmployeeResponse(null,"Something Went Wrong! Please try again!!!",null));
        }
    }

    /**
     * API endpoint to get employees with pagination and sorting options.
     *
     * @param page     The page number.
     * @param size     The page size.
     * @param sortBy   The field to sort by (e.g., name or email).
     */
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
