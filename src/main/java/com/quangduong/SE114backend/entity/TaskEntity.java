package com.quangduong.SE114backend.entity;

import com.quangduong.SE114backend.constant.TaskStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task")
public class TaskEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "status")
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableEntity table;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TextAttributeEntity> textAttributes = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<NumberAttributeEntity> numberAttributes = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<DateAttributeEntity> dateAttributes = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<LabelAttributeEntity> labelAttributes = new ArrayList<>();

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TableEntity getTable() {
        return table;
    }

    public void setTable(TableEntity table) {
        this.table = table;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public List<TextAttributeEntity> getTextAttributes() {
        return textAttributes;
    }

    public void setTextAttributes(List<TextAttributeEntity> textAttributes) {
        this.textAttributes = textAttributes;
    }

    public List<NumberAttributeEntity> getNumberAttributes() {
        return numberAttributes;
    }

    public void setNumberAttributes(List<NumberAttributeEntity> numberAttributes) {
        this.numberAttributes = numberAttributes;
    }

    public List<DateAttributeEntity> getDateAttributes() {
        return dateAttributes;
    }

    public void setDateAttributes(List<DateAttributeEntity> dateAttributes) {
        this.dateAttributes = dateAttributes;
    }

    public List<LabelAttributeEntity> getLabelAttributes() {
        return labelAttributes;
    }

    public void setLabelAttributes(List<LabelAttributeEntity> labelAttributes) {
        this.labelAttributes = labelAttributes;
    }
}
