package com.quangduong.SE114backend.mapper;

import com.quangduong.SE114backend.constant.UserStatus;
import com.quangduong.SE114backend.dto.user.UserBoardDTO;
import com.quangduong.SE114backend.dto.user.UserDTO;
import com.quangduong.SE114backend.dto.user.UserInfoDTO;
import com.quangduong.SE114backend.entity.BoardEntity;
import com.quangduong.SE114backend.entity.UserEntity;
import com.quangduong.SE114backend.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<BoardEntity> boardEntities = new ArrayList<>();
        boardEntities.addAll(entity.getOwnerBoards());
        boardEntities.addAll(entity.getBoards());
        boardEntities = boardEntities.stream().sorted((o1, o2) -> o2.getCreatedDate().compareTo(o1.getCreatedDate())).collect(Collectors.toList());
        dto.setBoards(boardEntities.stream().map(b -> new UserBoardDTO(b.getId(), b.getName())).collect(Collectors.toList()));
        return dto;
    }

    public UserInfoDTO userInfoDTO(UserEntity entity) {
        UserInfoDTO dto = new UserInfoDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setPhotoUrl(entity.getPhotoUrl());
        return dto;
    }

    public UserDTO toDTO(UserModel model) {
        UserDTO dto = new UserDTO();
        dto.setId(model.getUserId());
        dto.setDisplayName(model.getDisplayName());
        dto.setFamilyName(model.getFamilyName());
        dto.setGivenName(model.getGivenName());
        dto.setEmail(model.getEmail());
        dto.setPhotoUrl(model.getPhotoUrl());
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

    public UserModel toModel(UserEntity entity) {
        UserModel model = new UserModel();
        model.setUserId(entity.getId());
        model.setEmail(entity.getEmail());
        model.setDisplayName(entity.getDisplayName());
        model.setFamilyName(entity.getFamilyName());
        model.setGivenName(entity.getGivenName());
        model.setPhotoUrl(entity.getPhotoUrl());
        return model;
    }

}
