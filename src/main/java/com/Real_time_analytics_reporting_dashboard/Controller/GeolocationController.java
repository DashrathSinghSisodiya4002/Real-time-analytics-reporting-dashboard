package com.Real_time_analytics_reporting_dashboard.Controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Real_time_analytics_reporting_dashboard.Entity.Geolocation;
import com.Real_time_analytics_reporting_dashboard.Repository.GeolocationRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/geolocations")
@RequiredArgsConstructor
public class GeolocationController {

    private final GeolocationRepository geolocationRepository;

    @PostMapping
    public ResponseEntity<Geolocation> createGeolocation(
            @RequestBody Geolocation geolocation) {

        return ResponseEntity.ok(
                geolocationRepository.save(geolocation)
        );
    }

    @GetMapping
    public ResponseEntity<List<Geolocation>> getAllGeolocations() {

        return ResponseEntity.ok(
                geolocationRepository.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Geolocation> getGeolocationById(
            @PathVariable Long id) {

        return geolocationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGeolocation(
            @PathVariable Long id) {

        if (!geolocationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        geolocationRepository.deleteById(id);

        return ResponseEntity.ok(
                "Geolocation deleted successfully"
        );
    }
}