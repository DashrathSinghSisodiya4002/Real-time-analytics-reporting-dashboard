package com.Real_time_analytics_reporting_dashboard.Specification;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.Real_time_analytics_reporting_dashboard.Entity.ActivityLog;

public class ActivityLogSpecification {

    public static Specification<ActivityLog> hasActionType(String actionType) {

        return (root, query, criteriaBuilder) -> {

            if (actionType == null || actionType.isBlank()) {
                return null;
            }

            return criteriaBuilder.equal(
                    root.get("actionType"),
                    actionType
            );
        };
    }

    public static Specification<ActivityLog> hasStartDate(LocalDateTime startDate) {

        return (root, query, criteriaBuilder) -> {

            if (startDate == null) {
                return null;
            }

            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get("timestamp"),
                    startDate
            );
        };
    }

    public static Specification<ActivityLog> hasEndDate(LocalDateTime endDate) {

        return (root, query, criteriaBuilder) -> {

            if (endDate == null) {
                return null;
            }

            return criteriaBuilder.lessThanOrEqualTo(
                    root.get("timestamp"),
                    endDate
            );
        };
    }
}