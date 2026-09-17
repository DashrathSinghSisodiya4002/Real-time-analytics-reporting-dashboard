package com.Real_time_analytics_reporting_dashboard.Service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.Real_time_analytics_reporting_dashboard.Entity.ActivityLog;
import com.Real_time_analytics_reporting_dashboard.Exception.ResourceNotFoundException;
import com.Real_time_analytics_reporting_dashboard.Repository.ActivityLogRepository;
import com.Real_time_analytics_reporting_dashboard.Specification.ActivityLogSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    @Override
    public ActivityLog createActivityLog(ActivityLog activityLog) {

        return activityLogRepository.save(activityLog);
    }

    @Override
    public List<ActivityLog> getAllActivityLogs() {

        return activityLogRepository.findAll();
    }

    @Override
    public ActivityLog getActivityLogById(Long id) {

        return activityLogRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Activity Log not found with id: " + id
                        )
                );
    }

    @Override
    public ActivityLog updateActivityLog(
            Long id,
            ActivityLog activityLog) {

        ActivityLog existingLog =
                activityLogRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Activity Log not found with id: " + id
                                )
                        );

        existingLog.setActionType(activityLog.getActionType());
        existingLog.setIpAddress(activityLog.getIpAddress());
        existingLog.setTimestamp(activityLog.getTimestamp());
        existingLog.setUserAgent(activityLog.getUserAgent());
        existingLog.setGeolocation(activityLog.getGeolocation());
        existingLog.setUser(activityLog.getUser());

        return activityLogRepository.save(existingLog);
    }

    @Override
    public void deleteActivityLog(Long id) {

        if (!activityLogRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Activity Log not found with id: " + id
            );
        }

        activityLogRepository.deleteById(id);
    }

    @Override
    public List<ActivityLog> searchActivityLogs(
            String actionType,
            LocalDateTime startDate,
            LocalDateTime endDate) {

        Specification<ActivityLog> specification =
                Specification.where(
                        ActivityLogSpecification.hasActionType(actionType)
                ).and(
                        ActivityLogSpecification.hasStartDate(startDate)
                ).and(
                        ActivityLogSpecification.hasEndDate(endDate)
                );

        return activityLogRepository.findAll(specification);
    }
}