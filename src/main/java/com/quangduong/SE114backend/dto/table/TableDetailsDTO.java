package com.quangduong.SE114backend.dto.table;

import com.quangduong.SE114backend.dto.task.TaskDetailsDTO;
import com.quangduong.SE114backend.dto.user.UserInfoDTO;

import java.util.List;

public class TableDetailsDTO {

    private long id;

    private UserInfoDTO createdBy;

    private String name;

    private String description;

    private List<UserInfoDTO> members;

    private List<TaskDetailsDTO> tasks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserInfoDTO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserInfoDTO createdBy) {
        this.createdBy = createdBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserInfoDTO> getMembers() {
        return members;
    }

    public void setMembers(List<UserInfoDTO> members) {
        this.members = members;
    }

    public List<TaskDetailsDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDetailsDTO> tasks) {
        this.tasks = tasks;
    }
}
