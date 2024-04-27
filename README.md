## Tech Stack
- Spring Boot
- PostgreSQL

## How to Run the Project

### Using GitHub
1. Clone the repository:
   ```bash
   git clone https://github.com/RickyParte/Employee-Backend.git
   ```

2. Create a database using pgAdmin or DBeaver with the name `employee`.

3. Update the database configuration in the application.properties file:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/employee?currentSchema=employee
   spring.datasource.username=postgres
   spring.datasource.password=admin
   ```

4. Run the project and check if it is working fine.

### Using Deployed Server
1. Access the deployed server at [deployed_link](#).

2. Test APIs using Postman or Swagger.

### Using Zip
1. Extract the folder and open it in your favorite IDE.

2. Create a database using pgAdmin or DBeaver with the name `employee`.

3. Update the database configuration in the application.properties file as mentioned above.

4. Run the project and check if it is working fine.

## API Documentation

### Add Employee
- **Endpoint:** POST /api/employee/add-employee
- **Request URL:** localhost:8080/api/employee/add-employee
- **Request Body:**
  ```json
  {
    "employeeName": "Ricky Parte Email",
    "phoneNumber": "892500075",
    "email": "rickydipakparte20243@gmail.com",
    "reportsTo": "c971cfd3-fa8a-4c58-93c7-1c93f5916e34",
    "profileImage": "null"
  }
  ```
- **Response:**
- CURL
  ```
  curl --location 'localhost:8080/api/employee/add-employee' \
     --header 'Content-Type: application/json' \
     --data-raw '{
        "employeeName": "Ricky Parte Email",
        "phoneNumber": "892500075",
        "email": "rickydipakparte20243@gmail.com",
        "reportsTo": "c971cfd3-fa8a-4c58-93c7-1c93f5916e34",
        "profileImage": "null"
     }'
  ```

### Get All Employees
- **Endpoint:** GET /api/employee/get-employees
- **Request URL:** localhost:8080/api/employee/get-employees
- **Response:**
- - CURL
  ```
     curl --location 'localhost:8080/api/employee/get-employees'
  ```

### Delete Employee By ID
- **Endpoint:** POST /api/employee/delete-employee-by-id
- **Request URL:** localhost:8080/api/employee/delete-employee-by-id
- **Request Header:** `employeeId` -> value of employeeId
- **Response:**
- CURL
  ```
  curl --location --request POST 'localhost:8080/api/employee/delete-employee-by-id' \
   --header 'employeeId: 9bafd606-732e-4076-8c34-c5e5e632b07f'
  ```


### Update Employee
- **Endpoint:** POST /api/employee/update-employee/{employeeId}
- **Request URL:** localhost:8080/api/employee/update-employee/{employeeId}
- **Request Body:**
  ```json
  {
    "employeeName": "Ricky Parte Work",
    "phoneNumber": "4568536948",
    "email": "fs19if043@gmail.com",
    "reportsTo": "null",
    "profileImage": "null"
  }
  ```
- **Response:**
- CURL
  ```
    curl --location 'localhost:8080/api/employee/update-employee/f9be16f7-00f3-48f9-b255-b867193cac23' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "employeeName": "Ricky Parte Work",
   "phoneNumber": "4568536948",
   "email": "fs19if043@gmail.com",
   "reportsTo": "null",
   "profileImage": "null"
   }'
  ```

### Get Nth Manager
- **Endpoint:** POST /api/employee/get-nth-manager
- **Request URL:** localhost:8080/api/employee/get-nth-manager
- **Request Header:** 
  - `employeeId` -> value of employeeId
  - `managerLevel` -> value of level 
- **Response:**
- CURL
  ```
  curl --location --request POST 'localhost:8080/api/employee/get-nth-manager' \
   --header 'employeeId: c971cfd3-fa8a-4c58-93c7-1c93f5916e34' \
   --header 'managerLevel: 4' \
   --data ''
  ```

### Get Employee With Pagination
- **Endpoint:** GET /api/employee/get-all-employees
- **Request URL:** localhost:8080/api/employee/get-all-employees?page=0&size=5&sort=employeeName
- **Request Param:** page=0&size=5&sort=employeeName
- **Response:**
- CURL
  ```
  curl --location 'localhost:8080/api/employee/get-all-employees?page=0&size=5&sort=employeeName'
  ```

```
