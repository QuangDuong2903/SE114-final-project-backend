package com.quangduong.SE114backend.mapper;

import com.quangduong.SE114backend.dto.label.LabelDTO;
import com.quangduong.SE114backend.dto.label.LabelUpdateDTO;
import com.quangduong.SE114backend.entity.LabelEntity;
import com.quangduong.SE114backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LabelMapper {

    @Autowired
    private SecurityUtils securityUtils;

    public LabelDTO toDTO(LabelEntity entity) {
        LabelDTO dto = new LabelDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setColor(entity.getColor());
        return dto;
    }

    public LabelEntity toEntity(LabelDTO dto) {
        LabelEntity entity = new LabelEntity();
        entity.setName(dto.getName());
        entity.setColor(dto.getColor());
        entity.setUser(securityUtils.getCurrentUser());
        return entity;
    }

    public LabelEntity toEntity(LabelUpdateDTO dto, LabelEntity entity) {
        if(dto.getName() != null && !dto.getName().isBlank())
            entity.setName(dto.getName());
        if(dto.getColor() != null && !dto.getColor().isBlank())
            entity.setColor(dto.getColor());
        return entity;
    }
}
