package com.quangduong.SE114backend.dto.board;

import java.util.List;

public class BoardUpdateDTO {

    private long id;

    private String name;

    private long adminId;

    private List<Long> membersIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public List<Long> getMembersIds() {
        return membersIds;
    }

    public void setMembersIds(List<Long> membersIds) {
        this.membersIds = membersIds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
