package com.Real_time_analytics_reporting_dashboard.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_agents")

@Cache(
        usage = CacheConcurrencyStrategy.READ_ONLY,
        region = "userAgentCache"
)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String browser;

    private String browserVersion;

    private String operatingSystem;

    private String deviceType;
}