package com.Real_time_analytics_reporting_dashboard.Service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import org.springframework.data.jpa.domain.Specification;

import com.Real_time_analytics_reporting_dashboard.Entity.ActivityLog;
import com.Real_time_analytics_reporting_dashboard.Repository.ActivityLogRepository;

@ExtendWith(MockitoExtension.class)
class ActivityLogServiceImplTest {

    @Mock
    private ActivityLogRepository activityLogRepository;

    @InjectMocks
    private ActivityLogServiceImpl activityLogService;

    @Test
    void createActivityLog_shouldSaveAndReturnLog() {

        ActivityLog log = new ActivityLog();
        log.setId(1L);
        log.setActionType("LOGIN");

        when(activityLogRepository.save(log)).thenReturn(log);

        ActivityLog result = activityLogService.createActivityLog(log);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("LOGIN", result.getActionType());

        verify(activityLogRepository).save(log);
    }

    @Test
    void getAllActivityLogs_shouldReturnLogs() {

        ActivityLog log = new ActivityLog();
        log.setId(1L);
        log.setActionType("LOGIN");

        when(activityLogRepository.findAll())
                .thenReturn(List.of(log));

        List<ActivityLog> result =
                activityLogService.getAllActivityLogs();

        assertEquals(1, result.size());
        assertEquals("LOGIN", result.get(0).getActionType());

        verify(activityLogRepository).findAll();
    }

    @Test
    void getActivityLogById_shouldReturnLog() {

        ActivityLog log = new ActivityLog();
        log.setId(1L);
        log.setActionType("LOGIN");

        when(activityLogRepository.findById(1L))
                .thenReturn(Optional.of(log));

        ActivityLog result =
                activityLogService.getActivityLogById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("LOGIN", result.getActionType());

        verify(activityLogRepository).findById(1L);
    }

    @Test
    void getActivityLogById_shouldThrowExceptionWhenNotFound() {

        when(activityLogRepository.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> activityLogService.getActivityLogById(99L)
                );

        assertEquals(
                "Activity Log not found with id: 99",
                exception.getMessage()
        );

        verify(activityLogRepository).findById(99L);
    }

    @Test
    void deleteActivityLog_shouldDeleteWhenExists() {

        when(activityLogRepository.existsById(1L))
                .thenReturn(true);

        activityLogService.deleteActivityLog(1L);

        verify(activityLogRepository).existsById(1L);
        verify(activityLogRepository).deleteById(1L);
    }

    @Test
    void deleteActivityLog_shouldThrowExceptionWhenNotFound() {

        when(activityLogRepository.existsById(99L))
                .thenReturn(false);

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> activityLogService.deleteActivityLog(99L)
                );

        assertEquals(
                "Activity Log not found with id: 99",
                exception.getMessage()
        );

        verify(activityLogRepository).existsById(99L);
        verify(activityLogRepository, never()).deleteById(99L);
    }

    @Test
    void searchActivityLogs_shouldReturnFilteredLogs() {

        ActivityLog log = new ActivityLog();
        log.setId(1L);
        log.setActionType("LOGIN");
        log.setTimestamp(
                LocalDateTime.of(2026, 9, 13, 23, 25)
        );

        when(activityLogRepository.findAll(
                any(Specification.class)
        )).thenReturn(List.of(log));

        List<ActivityLog> result =
                activityLogService.searchActivityLogs(
                        "LOGIN",
                        LocalDateTime.of(2026, 9, 13, 0, 0),
                        LocalDateTime.of(2026, 9, 14, 0, 0)
                );

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("LOGIN", result.get(0).getActionType());

        verify(activityLogRepository)
                .findAll(any(Specification.class));
    }
}