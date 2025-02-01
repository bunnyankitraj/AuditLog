package com.example.AuditLog.event;

import com.example.AuditLog.entity.AuditLog;
import com.example.AuditLog.entity.AuditRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EventListenerComponent {

    @Autowired
    private AuditRepository auditRepository;

    @Async
    @EventListener
    public void handleCustomEvent(CustomEvent event) throws JsonProcessingException {
        System.out.println("Received event: " + event);
        AuditLog auditLog = event.getAuditLog();

        auditRepository.save(auditLog);

//        String logString = event.getMessage();
//
//        // Convert string to JSON format
//        String json = logString.replace("AuditLog(", "{")
//                .replace(")", "}")
//                .replace("=", "\":\"")
//                .replace(", ", "\", \"")
//                .replace("id:\"null\"", "id:null"); // Handling null case
//
//        json = json.replaceAll("(\\w+):", "\"$1\":"); // Enclose keys in quotes
//
//        // Convert JSON to Object
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.findAndRegisterModules(); // To handle LocalDateTime
//
//        AuditLog auditLog = objectMapper.readValue(json, AuditLog.class);
//
//        System.out.println(auditLog);
//
//        auditRepository.save(new AuditLog());
//
    }
}
