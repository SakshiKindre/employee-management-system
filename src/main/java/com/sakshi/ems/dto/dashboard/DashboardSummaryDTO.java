package com.sakshi.ems.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSummaryDTO {

    private long totalEmployees;

    private long activeEmployees;

    private long departmentCount;

    private double averageSalary;

}