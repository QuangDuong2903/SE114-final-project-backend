package com.quangduong.SE114backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "number_attribute")
public class NumberAttributeEntity extends AttributeEntity {

    @Column(name = "value")
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
