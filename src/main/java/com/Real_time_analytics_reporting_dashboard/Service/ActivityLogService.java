package com.Real_time_analytics_reporting_dashboard.Service;


import java.time.LocalDateTime;
import java.util.List;

import com.Real_time_analytics_reporting_dashboard.Entity.ActivityLog;

public interface ActivityLogService {

    ActivityLog createActivityLog(ActivityLog activityLog);

    List<ActivityLog> getAllActivityLogs();

    ActivityLog getActivityLogById(Long id);

    ActivityLog updateActivityLog(Long id, ActivityLog activityLog);

    void deleteActivityLog(Long id);

    List<ActivityLog> searchActivityLogs(
            String actionType,
            LocalDateTime startDate,
            LocalDateTime endDate
    );
}