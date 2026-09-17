package com.Real_time_analytics_reporting_dashboard.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Real_time_analytics_reporting_dashboard.Repository.ReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public List<Object[]> getActionTypeReport() {

        return reportRepository.getActionTypeReport();
    }
}