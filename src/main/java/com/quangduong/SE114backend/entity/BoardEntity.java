package com.quangduong.SE114backend.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
public class BoardEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private UserEntity admin;

    @ManyToMany
    @JoinTable(name = "user_board", joinColumns = @JoinColumn(name = "board_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> members = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<TableEntity> tables = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getAdmin() {
        return admin;
    }

    public void setAdmin(UserEntity admin) {
        this.admin = admin;
    }

    public List<UserEntity> getMembers() {
        return members;
    }

    public void setMembers(List<UserEntity> members) {
        this.members = members;
    }

    public List<TableEntity> getTables() {
        return tables;
    }

    public void setTables(List<TableEntity> tables) {
        this.tables = tables;
    }
}
