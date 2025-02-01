package com.example.AuditLog.event;

import com.example.AuditLog.entity.AuditLog;
import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {

    private final AuditLog auditLog;

    public CustomEvent(Object source, AuditLog auditLog) {
        super(source);
        this.auditLog = auditLog;
    }

    public AuditLog getAuditLog() {
        return auditLog;
    }
}
