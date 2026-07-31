package com.sakshi.ems.controller;

import com.sakshi.ems.dto.employee.EmployeeDTO;
import com.sakshi.ems.response.ApiResponse;
import com.sakshi.ems.response.PaginationMeta;
import com.sakshi.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Create Employee
    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDTO>> saveEmployee(
            @Valid @RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO savedEmployee = employeeService.saveEmployee(employeeDTO);

        ApiResponse<EmployeeDTO> response = ApiResponse.<EmployeeDTO>builder()
                .success(true)
                .message("Employee created successfully")
                .timestamp(LocalDateTime.now())
                .data(savedEmployee)
                .meta(null)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Employees with Pagination & Sorting
    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAllEmployees(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Page<EmployeeDTO> employees =
                employeeService.getAllEmployees(page, size, sortBy, direction);

        PaginationMeta meta = PaginationMeta.builder()
                .page(employees.getNumber())
                .size(employees.getSize())
                .totalElements(employees.getTotalElements())
                .totalPages(employees.getTotalPages())
                .last(employees.isLast())
                .build();

        ApiResponse<List<EmployeeDTO>> response =
                ApiResponse.<List<EmployeeDTO>>builder()
                        .success(true)
                        .message("Employees fetched successfully")
                        .timestamp(LocalDateTime.now())
                        .data(employees.getContent())
                        .meta(meta)
                        .build();

        return ResponseEntity.ok(response);
    }

    // Get Employee By ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployeeById(
            @PathVariable Long id) {

        EmployeeDTO employee = employeeService.getEmployeeById(id);

        ApiResponse<EmployeeDTO> response = ApiResponse.<EmployeeDTO>builder()
                .success(true)
                .message("Employee fetched successfully")
                .timestamp(LocalDateTime.now())
                .data(employee)
                .meta(null)
                .build();

        return ResponseEntity.ok(response);
    }

    // Search Employees with Pagination & Sorting
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> searchEmployees(

            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Page<EmployeeDTO> employees =
                employeeService.searchEmployees(
                        keyword,
                        page,
                        size,
                        sortBy,
                        direction);

        PaginationMeta meta = PaginationMeta.builder()
                .page(employees.getNumber())
                .size(employees.getSize())
                .totalElements(employees.getTotalElements())
                .totalPages(employees.getTotalPages())
                .last(employees.isLast())
                .build();

        ApiResponse<List<EmployeeDTO>> response =
                ApiResponse.<List<EmployeeDTO>>builder()
                        .success(true)
                        .message("Employees fetched successfully")
                        .timestamp(LocalDateTime.now())
                        .data(employees.getContent())
                        .meta(meta)
                        .build();

        return ResponseEntity.ok(response);
    }

    // Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO updatedEmployee =
                employeeService.updateEmployee(id, employeeDTO);

        ApiResponse<EmployeeDTO> response =
                ApiResponse.<EmployeeDTO>builder()
                        .success(true)
                        .message("Employee updated successfully")
                        .timestamp(LocalDateTime.now())
                        .data(updatedEmployee)
                        .meta(null)
                        .build();

        return ResponseEntity.ok(response);
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .success(true)
                        .message("Employee deleted successfully")
                        .timestamp(LocalDateTime.now())
                        .data(null)
                        .meta(null)
                        .build();

        return ResponseEntity.ok(response);
    }
}