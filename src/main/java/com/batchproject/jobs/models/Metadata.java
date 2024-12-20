package com.batchproject.jobs.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

//for keeping things like
/*
wash price, dry price
has basement
 */
@Table(name = "metadata", indexes = @Index(columnList = "table_name"))
@Entity
public class Metadata extends BaseEntity{
    @Column(name = "table_name")
    String tableName;

    @Column(name = "reference_id")
    Integer referenceId;

    @Column(name = "key")
    String key;

    @Column(name = "value")
    String value;
}
