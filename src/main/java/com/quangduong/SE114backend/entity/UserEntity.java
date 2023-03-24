package com.quangduong.SE114backend.entity;

import com.quangduong.SE114backend.constant.UserStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "displayname")
    private String displayName;

    @Column(name = "givenname")
    private String givenName;

    @Column(name = "familyname")
    private String familyName;

    @Column(name = "photourl")
    private String photoUrl;

    @Column(name = "status")
    private UserStatus status;

    @OneToMany(mappedBy = "admin")
    private List<BoardEntity> ownerBoards = new ArrayList<>();

    @ManyToMany(mappedBy = "members")
    private List<BoardEntity> boards = new ArrayList<>();

    @ManyToMany(mappedBy = "members")
    private List<TableEntity> tables = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<LabelEntity> labels = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<TaskEntity> tasks = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<BoardEntity> getOwnerBoards() {
        return ownerBoards;
    }

    public void setOwnerBoards(List<BoardEntity> ownerBoards) {
        this.ownerBoards = ownerBoards;
    }

    public List<BoardEntity> getBoards() {
        return boards;
    }

    public void setBoards(List<BoardEntity> boards) {
        this.boards = boards;
    }

    public List<TableEntity> getTables() {
        return tables;
    }

    public void setTables(List<TableEntity> tables) {
        this.tables = tables;
    }

    public List<LabelEntity> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelEntity> labels) {
        this.labels = labels;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
