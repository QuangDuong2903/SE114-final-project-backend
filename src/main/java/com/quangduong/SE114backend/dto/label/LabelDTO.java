package com.quangduong.SE114backend.dto.label;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LabelDTO {

    private long id;
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Color is required")
    private String color;

    @NotNull(message = "Board id is required")
    private Long boardId;

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
