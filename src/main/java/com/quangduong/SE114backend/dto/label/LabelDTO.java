package com.quangduong.SE114backend.dto.label;

import jakarta.validation.constraints.NotBlank;

public class LabelDTO {

    private long id;
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Color is required")
    private String color;

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
