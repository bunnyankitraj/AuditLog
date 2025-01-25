package com.example.AuditLog.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<AuditLog,Integer> {
}
