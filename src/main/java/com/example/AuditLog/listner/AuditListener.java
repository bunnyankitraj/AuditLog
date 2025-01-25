package com.example.AuditLog.listner;

import com.example.AuditLog.entity.AuditLog;
import com.example.AuditLog.entity.AuditRepository;
import jakarta.transaction.Transactional;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuditListener implements PostUpdateEventListener, PostInsertEventListener, PostDeleteEventListener {

    @Autowired
    private AuditRepository auditRepository;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    private void logChanges(Object entity, String operation, Object[] oldState, Object[] newState, String[] propertyNames) {
        if (entity instanceof AuditLog) {
            return;
        }

        try {
            // Your existing code for saving the audit log
            AuditLog auditLog = new AuditLog();
            auditLog.setTableName(entity.getClass().getSimpleName());
            auditLog.setOperationType(operation);
            auditLog.setChangedBy("Ankit");
            auditLog.setTimestamp(LocalDateTime.now());
            auditLog.setEntityId("UNKNOWN");
            auditLog.setTraceId("traceId");
            auditLog.setChanges("Changes as per logic");

            auditRepository.save(auditLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onPostInsert(PostInsertEvent event) {
        System.out.println("🔹 Post triggered for: " + event.getEntity().getClass().getSimpleName());
        logChanges(event.getEntity(), "INSERT", null, event.getState(), event.getPersister().getPropertyNames());

    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister entityPersister) {
        return false;
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        logChanges(event.getEntity(), "UPDATE", null, event.getState(), event.getPersister().getPropertyNames());
    }


    @Override
    public void onPostDelete(PostDeleteEvent event) {
        logChanges(event.getEntity(), "DELETE", null, null, event.getPersister().getPropertyNames());
    }
}

