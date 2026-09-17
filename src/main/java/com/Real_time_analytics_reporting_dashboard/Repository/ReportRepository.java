package com.Real_time_analytics_reporting_dashboard.Repository;

import java.util.List;

import org.hibernate.jpa.HibernateHints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.Real_time_analytics_reporting_dashboard.Entity.ActivityLog;

import jakarta.persistence.QueryHint;

public interface ReportRepository extends JpaRepository<ActivityLog, Long> {

    @Query("""
            SELECT a.actionType, COUNT(a.id)
            FROM ActivityLog a
            GROUP BY a.actionType
            ORDER BY COUNT(a.id) DESC
            """)
    @QueryHints({
        @QueryHint(
            name = HibernateHints.HINT_CACHEABLE,
            value = "true"
        )
    })
    List<Object[]> getActionTypeReport();
}