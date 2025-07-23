package com.example.AuditLog.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Data
@Table(name = "audit_log")
public class AuditLog  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String traceId;

    private String tableName;
    private String entityId;

    private String operationType;
    private String changedBy;
    private LocalDateTime timestamp;

    @Column(columnDefinition = "TEXT")
    private String changes;
}