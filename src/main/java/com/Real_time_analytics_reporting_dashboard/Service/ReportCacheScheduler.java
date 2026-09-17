package com.Real_time_analytics_reporting_dashboard.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReportCacheScheduler {

    private final ReportService reportService;

    //@Scheduled(fixedRate = 300000)
    public void refreshReportCache() {

        reportService.getActionTypeReport();

        System.out.println(
                "Report cache refreshed successfully."
        );
    }
}