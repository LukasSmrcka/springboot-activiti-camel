package com.lukas.activiti.infrastructure.base.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "CREATED_WHEN", updatable = false)
    private LocalDateTime createdWhen = LocalDateTime.now();

    @Column(name = "UPDATED_WHEN", nullable = false)
    private LocalDateTime updatedWhen = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedWhen() {
        return createdWhen;
    }

    public void setCreatedWhen(LocalDateTime createdWhen) {
        this.createdWhen = createdWhen;
    }

    public LocalDateTime getUpdatedWhen() {
        return updatedWhen;
    }

    public void setUpdatedWhen(LocalDateTime updatedWhen) {
        this.updatedWhen = updatedWhen;
    }
}
