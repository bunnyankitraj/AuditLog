package com.example.AuditLog.config;

import com.example.AuditLog.listner.AuditListener;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateEventListenerConfig {

    private final AuditListener auditListener;

    @Autowired
    public HibernateEventListenerConfig(AuditListener auditListener) {
        this.auditListener = auditListener;
    }

    @Autowired
    public void registerListeners(EntityManagerFactory entityManagerFactory) {
        SessionFactoryImplementor sessionFactory = entityManagerFactory.unwrap(SessionFactoryImplementor.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);

        // Use the Spring-managed AuditListener bean
//        registry.appendListeners(EventType.PRE_INSERT, auditListener);
//        registry.appendListeners(EventType.PRE_UPDATE, auditListener);
//        registry.appendListeners(EventType.PRE_DELETE, auditListener);
        registry.appendListeners(EventType.POST_INSERT, auditListener);
        registry.appendListeners(EventType.POST_UPDATE, auditListener);
        registry.appendListeners(EventType.POST_DELETE, auditListener);
    }
}