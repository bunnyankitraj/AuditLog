package com.example.AuditLog.event;

import com.example.AuditLog.entity.AuditLog;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
    private final ApplicationEventPublisher publisher;

    public EventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishEvent(AuditLog auditLog) {
        publisher.publishEvent(new CustomEvent(this, auditLog));
    }
}
