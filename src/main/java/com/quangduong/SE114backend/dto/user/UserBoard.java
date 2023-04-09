package com.quangduong.SE114backend.dto.user;

public class UserBoard {

    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserBoard(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
