package com.quangduong.SE114backend.dto.task;

import com.quangduong.SE114backend.constant.TaskStatus;
import com.quangduong.SE114backend.dto.attribute.DateAttributeDTO;
import com.quangduong.SE114backend.dto.attribute.LabelAttributeDTO;
import com.quangduong.SE114backend.dto.attribute.NumberAttributeDTO;
import com.quangduong.SE114backend.dto.attribute.TextAttributeDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class TaskUpdateDTO {

    private long id;

    private TaskStatus status;

    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
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
