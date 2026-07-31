package com.sakshi.ems.service.impl;

import com.sakshi.ems.dto.dashboard.DashboardSummaryDTO;
import com.sakshi.ems.repository.EmployeeRepository;
import com.sakshi.ems.service.DashboardService;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final EmployeeRepository employeeRepository;

    public DashboardServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public DashboardSummaryDTO getDashboardSummary() {

        long totalEmployees = employeeRepository.count();

        long activeEmployees =
                employeeRepository.countByStatus("Active");

        long departmentCount =
                employeeRepository.countDepartments();

        Double averageSalary =
                employeeRepository.averageSalary();

        return DashboardSummaryDTO.builder()
                .totalEmployees(totalEmployees)
                .activeEmployees(activeEmployees)
                .departmentCount(departmentCount)
                .averageSalary(
                        averageSalary == null ? 0 : averageSalary
                )
                .build();

    }

}