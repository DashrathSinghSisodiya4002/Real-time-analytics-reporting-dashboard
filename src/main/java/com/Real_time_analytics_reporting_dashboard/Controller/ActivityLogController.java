package com.Real_time_analytics_reporting_dashboard.Controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Real_time_analytics_reporting_dashboard.Entity.ActivityLog;
import com.Real_time_analytics_reporting_dashboard.Service.ActivityLogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/activity-logs")
@RequiredArgsConstructor
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    // CREATE
    @PostMapping
    public ResponseEntity<ActivityLog> createActivityLog(
            @RequestBody ActivityLog activityLog) {

        return ResponseEntity.ok(
                activityLogService.createActivityLog(activityLog)
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<ActivityLog>> getAllActivityLogs() {

        return ResponseEntity.ok(
                activityLogService.getAllActivityLogs()
        );
    }

    // SEARCH - Criteria API
    @GetMapping("/search")
    public ResponseEntity<List<ActivityLog>> searchActivityLogs(
            @RequestParam(required = false) String actionType,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDate) {

        return ResponseEntity.ok(
                activityLogService.searchActivityLogs(
                        actionType,
                        startDate,
                        endDate
                )
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ActivityLog> getActivityLogById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                activityLogService.getActivityLogById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ActivityLog> updateActivityLog(
            @PathVariable Long id,
            @RequestBody ActivityLog activityLog) {

        return ResponseEntity.ok(
                activityLogService.updateActivityLog(
                        id,
                        activityLog
                )
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActivityLog(
            @PathVariable Long id) {

        activityLogService.deleteActivityLog(id);

        return ResponseEntity.ok(
                "Activity Log deleted successfully"
        );
    }
}