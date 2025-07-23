package com.example.AuditLog.listner;

import com.example.AuditLog.entity.AuditLog;
import com.example.AuditLog.entity.AuditRepository;
import com.example.AuditLog.entity.Product;
import com.example.AuditLog.entity.User;
import jakarta.transaction.Transactional;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

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
            AuditLog auditLog = new AuditLog();
            auditLog.setTableName(entity.getClass().getSimpleName());
            auditLog.setOperationType(operation);
            auditLog.setChangedBy("Ankit");
            auditLog.setTimestamp(LocalDateTime.now());
            
            // Get the entity identifier
            String entityId = getEntityIdentifier(entity);
            auditLog.setEntityId(entityId);
            
            // Generate a simple trace ID (you might want to use a more sophisticated approach)
            auditLog.setTraceId(UUID.randomUUID().toString());
            
            // Track the actual changes
            StringBuilder changes = new StringBuilder();
            if ("INSERT".equals(operation) || "UPDATE".equals(operation)) {
                for (int i = 0; i < propertyNames.length; i++) {
                    if (newState[i] != null) {
                        changes.append(propertyNames[i])
                              .append(": ")
                              .append(newState[i])
                              .append(", ");
                    }
                }
            }
            auditLog.setChanges(changes.toString());

            auditRepository.save(auditLog);
            auditRepository.flush();
        } catch (Exception e) {
            // Log the error but don't throw it to prevent transaction rollback
            System.err.println("Error in audit logging: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String getEntityIdentifier(Object entity) {
        if (entity instanceof Product) {
            return ((Product) entity).getCode();
        } else if (entity instanceof User) {
            return String.valueOf(((User) entity).getId());
        }
        return "UNKNOWN";
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
        try {
            System.out.println("🔹 Post triggered for: " + event.getEntity().getClass().getSimpleName());
            logChanges(event.getEntity(), "INSERT", null, event.getState(), event.getPersister().getPropertyNames());
        } catch (Exception e) {
            // Log the error but don't throw it to prevent transaction rollback
            System.err.println("Error in audit logging: " + e.getMessage());
            e.printStackTrace();
        }
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

