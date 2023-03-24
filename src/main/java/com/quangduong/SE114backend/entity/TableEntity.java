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

}
