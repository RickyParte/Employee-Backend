## Deployed API CURL

### Add Employee
- CURL
  ```
  curl --location 'https://employee-backend-production-74cc.up.railway.app/api/employee/add-employee' \
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
- - CURL
```
   curl --location 'https://employee-backend-production-74cc.up.railway.app/api/employee/get-employees'
```

### Delete Employee By ID
- CURL
  ```
  curl --location --request POST 'https://employee-backend-production-74cc.up.railway.app/api/employee/delete-employee-by-id' \
   --header 'employeeId: 9bafd606-732e-4076-8c34-c5e5e632b07f'
  ```


### Update Employee
- CURL
  ```
    curl --location 'https://employee-backend-production-74cc.up.railway.app/api/employee/update-employee/60fcc143-0d4b-4802-a702-106b7f0df1b9' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "employeeName": "Ricky Parte Email",
    "phoneNumber": "892500075",
    "email": "rickydipakparte20243@gmail.com",
    "reportsTo": "null",
    "profileImage": "null"
    }'
  ```

### Get Nth Manager
- CURL
  ```
  curl --location --request POST 'https://employee-backend-production-74cc.up.railway.app/api/employee/get-nth-manager' \
    --header 'employeeId: 60fcc143-0d4b-4802-a702-106b7f0df1b9' \
    --header 'managerLevel: 1'
  ```

### Get Employee With Pagination

- CURL
  ```
  curl --location 'https://employee-backend-production-74cc.up.railway.app/api/employee/get-all-employees?page=0&size=5&sort=employeeName'
  ```

```

