package com.lukas.activiti.infrastructure.base.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class VersionedEntity extends Entity {

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
