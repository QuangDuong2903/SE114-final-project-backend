package com.quangduong.SE114backend.mapper;

import com.quangduong.SE114backend.dto.attribute.TextAttributeDTO;
import com.quangduong.SE114backend.entity.TextAttributeEntity;
import org.springframework.stereotype.Component;

@Component
public class TextAttributeMapper {

    public TextAttributeEntity toEntity(TextAttributeDTO dto) {
        TextAttributeEntity entity = new TextAttributeEntity();
        entity.setName(dto.getName());
        entity.setValue(dto.getValue());
        return entity;
    }

    public TextAttributeEntity toEntity(TextAttributeDTO dto, TextAttributeEntity entity) {
        entity.setName(dto.getName());
        entity.setValue(dto.getValue());
        return entity;
    }

    public TextAttributeDTO toDTO(TextAttributeEntity entity) {
        TextAttributeDTO dto = new TextAttributeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setValue(entity.getValue());
        return dto;
    }

}
