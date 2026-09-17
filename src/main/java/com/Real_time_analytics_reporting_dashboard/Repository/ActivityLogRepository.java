package com.Real_time_analytics_reporting_dashboard.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.Real_time_analytics_reporting_dashboard.Entity.ActivityLog;

import jakarta.persistence.QueryHint;

public interface ActivityLogRepository
        extends JpaRepository<ActivityLog, Long>,
                JpaSpecificationExecutor<ActivityLog> {

    // Entity Graph
    @Override
    @EntityGraph(value = "ActivityLog.detail")
    List<ActivityLog> findAll();

    // Query Cache
    @Query("SELECT a FROM ActivityLog a WHERE a.actionType = :actionType")
    @QueryHints(@QueryHint(
            name = "org.hibernate.cacheable",
            value = "true"
    ))
    List<ActivityLog> findByActionTypeCached(String actionType);
}