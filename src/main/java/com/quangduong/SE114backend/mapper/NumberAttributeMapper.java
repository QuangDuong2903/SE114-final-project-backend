package com.quangduong.SE114backend.mapper;

import com.quangduong.SE114backend.dto.attribute.NumberAttributeDTO;
import com.quangduong.SE114backend.dto.attribute.TextAttributeDTO;
import com.quangduong.SE114backend.entity.NumberAttributeEntity;
import com.quangduong.SE114backend.entity.TextAttributeEntity;
import org.springframework.stereotype.Component;

@Component
public class NumberAttributeMapper {

    public NumberAttributeEntity toEntity(NumberAttributeDTO dto) {
        NumberAttributeEntity entity = new NumberAttributeEntity();
        entity.setName(dto.getName());
        entity.setValue(dto.getValue());
        return entity;
    }

    public NumberAttributeEntity toEntity(NumberAttributeDTO dto, NumberAttributeEntity entity) {
        entity.setName(dto.getName());
        entity.setValue(dto.getValue());
        return entity;
    }

    public NumberAttributeDTO toDTO(NumberAttributeEntity entity) {
        NumberAttributeDTO dto = new NumberAttributeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setValue(entity.getValue());
        return dto;
    }
}
