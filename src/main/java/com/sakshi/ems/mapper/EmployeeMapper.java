package com.sakshi.ems.mapper;

import com.sakshi.ems.dto.employee.EmployeeDTO;
import com.sakshi.ems.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee employee) {

        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .department(employee.getDepartment())
                .designation(employee.getDesignation())
                .salary(employee.getSalary())
                .joiningDate(employee.getJoiningDate())
                .status(employee.getStatus())
                .build();

    }

    public static Employee toEntity(EmployeeDTO dto) {

        return Employee.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .department(dto.getDepartment())
                .designation(dto.getDesignation())
                .salary(dto.getSalary())
                .joiningDate(dto.getJoiningDate())
                .status(dto.getStatus())
                .build();

    }

}