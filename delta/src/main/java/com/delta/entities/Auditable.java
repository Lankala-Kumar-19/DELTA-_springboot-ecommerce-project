package com.delta.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

	@CreatedDate
	@Column(name="created_at",updatable=false)
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	@Column(name="updated_at",nullable=false)
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void onCreate() {
	    this.createdAt = LocalDateTime.now();
	    this.updatedAt = this.createdAt;
	}

	@PreUpdate
	public void onUpdate() {
	    this.updatedAt = LocalDateTime.now();
	}

}

