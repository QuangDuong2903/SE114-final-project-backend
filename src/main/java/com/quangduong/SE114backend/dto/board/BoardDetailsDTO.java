package com.quangduong.SE114backend.dto.board;

import com.quangduong.SE114backend.dto.label.LabelDTO;
import com.quangduong.SE114backend.dto.table.TableDetailsDTO;
import com.quangduong.SE114backend.dto.user.UserInfoDTO;

import java.util.List;

public class BoardDetailsDTO {

    private long id;

    private String name;

    private UserInfoDTO admin;

    private List<UserInfoDTO> members;

    private List<TableDetailsDTO> tables;

    private List<LabelDTO> labels;

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

    public UserInfoDTO getAdmin() {
        return admin;
    }

    public void setAdmin(UserInfoDTO admin) {
        this.admin = admin;
    }

    public List<UserInfoDTO> getMembers() {
        return members;
    }

    public void setMembers(List<UserInfoDTO> members) {
        this.members = members;
    }

    public List<TableDetailsDTO> getTables() {
        return tables;
    }

    public void setTables(List<TableDetailsDTO> tables) {
        this.tables = tables;
    }

    public List<LabelDTO> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelDTO> labels) {
        this.labels = labels;
    }
}
