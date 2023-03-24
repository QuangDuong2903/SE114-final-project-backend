package com.quangduong.SE114backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "label_attribute")
public class LabelAttributeEntity extends AttributeEntity {

    @ManyToOne
    @JoinColumn(name = "label_id")
    private LabelEntity value;

    public LabelEntity getValue() {
        return value;
    }

    public void setValue(LabelEntity value) {
        this.value = value;
    }
}
