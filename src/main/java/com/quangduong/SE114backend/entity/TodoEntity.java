package com.quangduong.SE114backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class TodoEntity extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "is_done")
    private Boolean isDone;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
