package com.quangduong.SE114backend.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "label")
public class LabelEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @OneToMany(mappedBy = "value", cascade = CascadeType.ALL)
    private List<LabelAttributeEntity> labelAttributes = new ArrayList<>();

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

    public BoardEntity getBoard() {
        return board;
    }

    public void setBoard(BoardEntity board) {
        this.board = board;
    }

    public List<LabelAttributeEntity> getLabelAttributes() {
        return labelAttributes;
    }

    public void setLabelAttributes(List<LabelAttributeEntity> labelAttributes) {
        this.labelAttributes = labelAttributes;
    }
}
