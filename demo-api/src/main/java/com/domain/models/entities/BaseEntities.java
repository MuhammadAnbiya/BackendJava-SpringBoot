package com.domain.models.entities;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass // Anotasi ini menunjukkan bahwa kelas ini adalah superclass yang entitas-entitas lain akan mewarisi properti dan metode darinya
@EntityListeners(AuditingEntityListener.class) // Bagian dari Spring JPA untuk  @CreatedBy, @CreatedDate, @LastModifiedBy, dan @LastModifiedDate
public abstract class BaseEntities<T> { 
    
    @CreatedBy
    protected T createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP) // tipe temporal yang digunakan adalah TIMESTAMP, yang berarti tanggal dan waktu (baik tanggal maupun waktu akan disimpan).
    protected Date createdDate;

    @LastModifiedBy
    protected T updatedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)// tipe temporal yang digunakan adalah TIMESTAMP, yang berarti tanggal dan waktu (baik tanggal maupun waktu akan disimpan).
    protected Date updateDate;

    public T getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(T createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public T getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(T updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }    
}
