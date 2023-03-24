package com.quangduong.SE114backend.entity;

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
}
