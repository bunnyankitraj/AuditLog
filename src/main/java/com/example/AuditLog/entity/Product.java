package com.example.AuditLog.entity;

import com.example.AuditLog.annotations.DoAudit;
import com.example.AuditLog.listner.AuditListener;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@DoAudit
@Table(name = "product")
@EntityListeners(AuditListener.class)
public class Product implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "code", length = 50, nullable = false, unique = true, insertable = false, updatable = false)
    private String code; // Primary Key: code (VARCHAR(50))

    private String name;
    private Double price;
    private String make;

}