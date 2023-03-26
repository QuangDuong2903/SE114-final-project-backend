package com.quangduong.SE114backend.dto.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class BoardDTO {

    private long id;

    @NotBlank(message = "Name is required")
    private String name;

    private long adminId;

    //@NotEmpty(message = "Members is required")
    private List<Long> membersIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getMembersIds() {
        return membersIds;
    }

    public void setMembersIds(List<Long> membersIds) {
        this.membersIds = membersIds;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
