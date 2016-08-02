package com.lukas.activiti.infrastructure.base.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AggregateRoot extends VersionedEntity {
}
