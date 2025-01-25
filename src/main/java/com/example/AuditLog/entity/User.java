package com.example.AuditLog.entity;

import com.example.AuditLog.annotations.DoAudit;
import com.example.AuditLog.listner.AuditListener;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@DoAudit
@Table(name = "users")
@EntityListeners(AuditListener.class)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double price;

}