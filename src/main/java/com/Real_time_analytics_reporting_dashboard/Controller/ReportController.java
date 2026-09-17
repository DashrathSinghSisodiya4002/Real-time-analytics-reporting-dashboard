package com.Real_time_analytics_reporting_dashboard.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Real_time_analytics_reporting_dashboard.Service.ReportService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/action-types")
    public ResponseEntity<List<Object[]>> getActionTypeReport() {

        return ResponseEntity.ok(
                reportService.getActionTypeReport()
        );
    }
}