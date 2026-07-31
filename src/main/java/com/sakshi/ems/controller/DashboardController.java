package com.sakshi.ems.controller;

import com.sakshi.ems.dto.dashboard.DashboardSummaryDTO;
import com.sakshi.ems.response.ApiResponse;
import com.sakshi.ems.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<DashboardSummaryDTO>> getSummary() {

        DashboardSummaryDTO summary =
                dashboardService.getDashboardSummary();

        ApiResponse<DashboardSummaryDTO> response =
                ApiResponse.<DashboardSummaryDTO>builder()
                        .success(true)
                        .message("Dashboard summary fetched successfully")
                        .timestamp(LocalDateTime.now())
                        .data(summary)
                        .meta(null)
                        .build();

        return ResponseEntity.ok(response);

    }

}