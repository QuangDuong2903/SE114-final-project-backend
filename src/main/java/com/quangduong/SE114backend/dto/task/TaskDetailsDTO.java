package com.quangduong.SE114backend.dto.task;

import com.quangduong.SE114backend.constant.TaskStatus;
import com.quangduong.SE114backend.dto.attribute.DateAttributeDTO;
import com.quangduong.SE114backend.dto.attribute.LabelAttributeDTO;
import com.quangduong.SE114backend.dto.attribute.NumberAttributeDTO;
import com.quangduong.SE114backend.dto.attribute.TextAttributeDTO;
import com.quangduong.SE114backend.dto.user.UserInfoDTO;

import java.util.List;

public class TaskDetailsDTO {

    private long id;

    private String description;

    private TaskStatus status;

    private UserInfoDTO user;

    private List<TextAttributeDTO> textAttributes;

    private List<NumberAttributeDTO> numberAttributes;

    private List<DateAttributeDTO> dateAttributes;

    private List<LabelAttributeDTO> labelAttributes;

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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public UserInfoDTO getUser() {
        return user;
    }

    public void setUser(UserInfoDTO user) {
        this.user = user;
    }

    public List<TextAttributeDTO> getTextAttributes() {
        return textAttributes;
    }

    public void setTextAttributes(List<TextAttributeDTO> textAttributes) {
        this.textAttributes = textAttributes;
    }

    public List<NumberAttributeDTO> getNumberAttributes() {
        return numberAttributes;
    }

    public void setNumberAttributes(List<NumberAttributeDTO> numberAttributes) {
        this.numberAttributes = numberAttributes;
    }

    public List<DateAttributeDTO> getDateAttributes() {
        return dateAttributes;
    }

    public void setDateAttributes(List<DateAttributeDTO> dateAttributes) {
        this.dateAttributes = dateAttributes;
    }

    public List<LabelAttributeDTO> getLabelAttributes() {
        return labelAttributes;
    }

    public void setLabelAttributes(List<LabelAttributeDTO> labelAttributes) {
        this.labelAttributes = labelAttributes;
    }
}
