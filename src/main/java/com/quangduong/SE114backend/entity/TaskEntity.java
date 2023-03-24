package com.quangduong.SE114backend.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task")
public class TaskEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableEntity table;

    @OneToMany(mappedBy = "task")
    private List<TextAttributeEntity> textAttributes = new ArrayList<>();

    @OneToMany(mappedBy = "task")
    private List<NumberAttributeEntity> numberAttributes = new ArrayList<>();

    @OneToMany(mappedBy = "task")
    private List<DateAttributeEntity> dateAttributes = new ArrayList<>();

    @OneToMany(mappedBy = "task")
    private List<LabelAttributeEntity> labelAttributes = new ArrayList<>();
}
