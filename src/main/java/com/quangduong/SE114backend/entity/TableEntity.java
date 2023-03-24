package com.quangduong.SE114backend.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tables")
public class TableEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @ManyToMany
    @JoinTable(name = "user_table", joinColumns = @JoinColumn(name = "table_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> members = new ArrayList<>();

    @OneToMany(mappedBy = "table")
    private List<TaskEntity> tasks = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoardEntity getBoard() {
        return board;
    }

    public void setBoard(BoardEntity board) {
        this.board = board;
    }

    public List<UserEntity> getMembers() {
        return members;
    }

    public void setMembers(List<UserEntity> members) {
        this.members = members;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }
}
