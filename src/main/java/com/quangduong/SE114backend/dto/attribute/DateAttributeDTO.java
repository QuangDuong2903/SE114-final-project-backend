package com.quangduong.SE114backend.dto.attribute;

import java.util.Date;

public class DateAttributeDTO {

    private Long id;
    private String name;
    private Date value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }
}
