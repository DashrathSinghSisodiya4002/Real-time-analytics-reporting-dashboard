package com.Real_time_analytics_reporting_dashboard.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Real_time_analytics_reporting_dashboard.Entity.UserAgent;
import com.Real_time_analytics_reporting_dashboard.Repository.UserAgentRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user-agents")
@RequiredArgsConstructor
public class UserAgentController {

    private final UserAgentRepository userAgentRepository;

    @PostMapping
    public ResponseEntity<UserAgent> createUserAgent(
            @RequestBody UserAgent userAgent) {

        return ResponseEntity.ok(
                userAgentRepository.save(userAgent)
        );
    }

    @GetMapping
    public ResponseEntity<List<UserAgent>> getAllUserAgents() {

        return ResponseEntity.ok(
                userAgentRepository.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAgent> getUserAgentById(
            @PathVariable Long id) {

        return userAgentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserAgent(
            @PathVariable Long id) {

        if (!userAgentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        userAgentRepository.deleteById(id);

        return ResponseEntity.ok(
                "UserAgent deleted successfully"
        );
    }
}