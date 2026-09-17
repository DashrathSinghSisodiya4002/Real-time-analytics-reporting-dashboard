package com.Real_time_analytics_reporting_dashboard.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "activity_logs")

@NamedEntityGraph(
        name = "ActivityLog.detail",
        attributeNodes = {
                @NamedAttributeNode("userAgent"),
                @NamedAttributeNode("geolocation"),
                @NamedAttributeNode("user")
        }
)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String actionType;

    private String ipAddress;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "user_agent_id")
    private UserAgent userAgent;

    @ManyToOne
    @JoinColumn(name = "geolocation_id")
    private Geolocation geolocation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}