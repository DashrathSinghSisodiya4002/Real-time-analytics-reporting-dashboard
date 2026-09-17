package com.Real_time_analytics_reporting_dashboard.Service;

import com.Real_time_analytics_reporting_dashboard.Dto.AuthResponse;
import com.Real_time_analytics_reporting_dashboard.Dto.LoginRequest;
import com.Real_time_analytics_reporting_dashboard.Dto.RegisterRequest;

public interface AuthService {
	
	AuthResponse register(RegisterRequest request);
	
	AuthResponse login (LoginRequest request);

}
