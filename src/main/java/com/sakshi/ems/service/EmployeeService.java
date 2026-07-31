package com.sakshi.ems.service;

import com.sakshi.ems.dto.employee.EmployeeDTO;
import org.springframework.data.domain.Page;

public interface EmployeeService {

    // Create Employee
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    // Get All Employees with Pagination & Sorting
    Page<EmployeeDTO> getAllEmployees(
            int page,
            int size,
            String sortBy,
            String direction
    );

    // Get Employee By ID
    EmployeeDTO getEmployeeById(Long id);

    // Update Employee
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

    // Delete Employee
    void deleteEmployee(Long id);

    // Search Employees with Pagination & Sorting
    Page<EmployeeDTO> searchEmployees(
            String keyword,
            int page,
            int size,
            String sortBy,
            String direction
    );
}