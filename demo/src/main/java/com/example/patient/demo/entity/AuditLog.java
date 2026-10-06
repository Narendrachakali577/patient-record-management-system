package com.example.patient.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String action;
    private Long patientId;
    private LocalDateTime timestamp;

    public AuditLog() {
    }

    public AuditLog(String username, String action,
                    Long patientId, LocalDateTime timestamp) {
        this.username = username;
        this.action = action;
        this.patientId = patientId;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAction() {
        return action;
    }

    public Long getPatientId() {
        return patientId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}