package com.quangduong.SE114backend.mapper;

import com.quangduong.SE114backend.dto.attribute.DateAttributeDTO;
import com.quangduong.SE114backend.dto.attribute.TextAttributeDTO;
import com.quangduong.SE114backend.entity.DateAttributeEntity;
import com.quangduong.SE114backend.entity.TextAttributeEntity;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class DateAttributeMapper {

    public DateAttributeEntity toEntity(DateAttributeDTO dto) {
        DateAttributeEntity entity = new DateAttributeEntity();
        entity.setName(dto.getName());
        entity.setValue(dto.getValue());
        return entity;
    }

    public DateAttributeEntity toEntity(DateAttributeDTO dto, DateAttributeEntity entity) {
        entity.setName(dto.getName());
        entity.setValue(dto.getValue());
        return entity;
    }

    public DateAttributeDTO toDTO(DateAttributeEntity entity) {
        DateAttributeDTO dto = new DateAttributeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(entity.getValue());
//        calendar.add(Calendar.HOUR_OF_DAY, - 7);
//        dto.setValue(calendar.getTime());
        dto.setValue(entity.getValue());
        return dto;
    }
}
