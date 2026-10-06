package com.example.patient.demo.service;

import com.example.patient.demo.entity.AuditLog;
import com.example.patient.demo.repository.AuditLogRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void log(String action, Long patientId) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        AuditLog auditLog = new AuditLog(
                username,
                action,
                patientId,
                LocalDateTime.now()
        );

        auditLogRepository.save(auditLog);
    }
}
