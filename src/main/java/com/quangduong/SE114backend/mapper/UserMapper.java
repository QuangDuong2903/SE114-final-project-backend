package com.quangduong.SE114backend.mapper;

import com.quangduong.SE114backend.constant.UserStatus;
import com.quangduong.SE114backend.dto.user.UserDTO;
import com.quangduong.SE114backend.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setDisplayName(entity.getDisplayName());
        dto.setFamilyName(entity.getFamilyName());
        dto.setGivenName(entity.getGivenName());
        dto.setEmail(entity.getEmail());
        dto.setPhotoUrl(entity.getPhotoUrl());
        return dto;
    }

    public UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setDisplayName(dto.getDisplayName());
        entity.setFamilyName(dto.getFamilyName());
        entity.setGivenName(dto.getGivenName());
        entity.setEmail(dto.getEmail());
        entity.setPhotoUrl(dto.getPhotoUrl());
        entity.setStatus(UserStatus.ACTIVE);
        return entity;
    }

}
