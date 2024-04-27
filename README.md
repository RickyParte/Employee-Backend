# Tech Used
1. Spring Boot
2. PostgreSQL

# How to run project
1. Using Github
   a. git clone https://github.com/RickyParte/Employee-Backend.git
   b. create database using pgAdmin or dbeaver Database Name -> employee
   c. spring.datasource.url="Enter Your DB URL"  ex. -> "jdbc:postgresql://localhost:5432/employee?currentSchema=employee"
      spring.datasource.username= "Enter Your DB Username"  ex. -> "postgres"
      spring.datasource.password="Enter Your DB password"  ex. ->  "admin"
   d. Run project and check it is working fine.
   e. Testing APIS using Postman,swagger


2. Using Deployed Server
   a. deployed link
   b. Testing APIS using Postman,swagger


3. Using Zip
   a. extract folder and open in your favourite IDE
   b. create database using pgAdmin or dbeaver Database Name -> employee
   c. spring.datasource.url="Enter Your DB URL"  ex. -> "jdbc:postgresql://localhost:5432/employee?currentSchema=employee"
      spring.datasource.username= "Enter Your DB Username"  ex. -> "postgres"
      spring.datasource.password="Enter Your DB password"  ex. ->  "admin"
   d. Run project and check it is working fine.
   e. Testing APIS using Postman,swagger


#API DOC

1. Add Employee
  a. addEmployee
     Endpoint: POST /api/employee/add-employee
     URL: localhost:8080/api/employee/add-employee
     CURL: curl --location 'localhost:8080/api/employee/add-employee' \
           --header 'Content-Type: application/json' \
           --data-raw '{
           "employeeName": "Ricky Parte Email",
           "phoneNumber": "892500075",
           "email": "rickydipakparte20243@gmail.com",
           "reportsTo": "c971cfd3-fa8a-4c58-93c7-1c93f5916e34",
           "profileImage": "null"
          }'
     Request Body:
         {
            "employeeName": "Ricky Parte Email",
            "phoneNumber": "892500075",
            "email": "rickydipakparte20243@gmail.com",
            "reportsTo": "c971cfd3-fa8a-4c58-93c7-1c93f5916e34",
            "profileImage": "null"
        }

     Response: 


3. Get All Employees
   a. getAllEmployee
      URL: localhost:8080/api/employee/get-employees
      CURL: curl --location 'localhost:8080/api/employee/get-employees'
      Endpoint: GET /api/employee/get-employees
      Request Body: No request body
      Response: 

5. Delete Employee By ID
  a.  deleteEmployeeByEmployeeId
      Endpoint: POST /api/employee/delete-employee-by-id
      URL: localhost:8080/api/employee/delete-employee-by-id
      CURL: curl --location --request POST 'localhost:8080/api/employee/delete-employee-by-id' \
            --header 'employeeId: 9bafd606-732e-4076-8c34-c5e5e632b07f'
      Request Header: employeeId-> value of employeeId
      Response: 
      
   
7. Update Employee
  a. updateEmployee
     Endpoint: POST /api/employee/update-employee/{employeeId}
     URL: localhost:8080/api/employee/update-employee/f9be16f7-00f3-48f9-b255-b867193cac23
     CURL: curl --location 'localhost:8080/api/employee/update-employee/f9be16f7-00f3-48f9-b255-b867193cac23' \
            --header 'Content-Type: application/json' \
            --data-raw '{
            "employeeName": "Ricky Parte Work",
            "phoneNumber": "4568536948",
            "email": "fs19if043@gmail.com",
            "reportsTo": "null",
            "profileImage": "null"
        }'
     Request Body:
       {
            "employeeName": "Ricky Parte Work",
            "phoneNumber": "4568536948",
            "email": "fs19if043@gmail.com",
            "reportsTo": "null",
            "profileImage": "null"
       }

     Response: 
   
9. Get Nth Manager
  a. getNthManager
     Endpoint: POST /api/employee/get-nth-manager
     URL: localhost:8080/api/employee/get-nth-manager
     CURL: curl --location --request POST 'localhost:8080/api/employee/get-nth-manager' \
            --header 'employeeId: c971cfd3-fa8a-4c58-93c7-1c93f5916e34' \
            --header 'managerLevel: 4' \
            --data ''
     Request Header: employeeId-> value of employeeId
     Request Header: managerLevel -> value of level 
     Response:
  
11. Email Send to Level 1 Manager for employee working under
    a. same add employee API and inside that add employee call for send Mail
  
12. Get Employee With Pagination
   a. getEmployeeWithPagination
      Endpoint: GET /api/employee/get-all-employees
      URL: localhost:8080/api/employee/get-all-employees?page=0&size=5&sort=employeeName
      CURL: curl --location 'localhost:8080/api/employee/get-all-employees?page=0&size=5&sort=employeeName'
      Request Param: page=0&size=5&sort=employeeName
      Response:

   


