package com.metaway.petshopwebsocketsmongo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Document
@SQLDelete(sql = "UPDATE tb_breed SET deleted = TRUE WHERE id=?")
public class Breed {
    @Id
    private String id;
    private String description;
    private Boolean deleted = Boolean.FALSE;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;
    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;

    public Breed(){}

    public Breed(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public Breed(String id, String description, Boolean deleted, String createdBy, LocalDateTime creationDate, String lastUpdatedBy, LocalDateTime lastUpdatedDate) {
        this.id = id;
        this.description = description;
        this.deleted = deleted;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Breed breed = (Breed) o;
        return Objects.equals(id, breed.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
