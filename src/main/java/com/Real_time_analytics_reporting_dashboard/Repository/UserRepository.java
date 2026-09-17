package com.Real_time_analytics_reporting_dashboard.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Real_time_analytics_reporting_dashboard.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

}
