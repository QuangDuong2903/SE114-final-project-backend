package com.quangduong.SE114backend.mapper;

import com.quangduong.SE114backend.dto.board.BoardDTO;
import com.quangduong.SE114backend.dto.board.BoardDetailsDTO;
import com.quangduong.SE114backend.dto.board.BoardUpdateDTO;
import com.quangduong.SE114backend.dto.user.UserBoardDTO;
import com.quangduong.SE114backend.entity.BoardEntity;
import com.quangduong.SE114backend.exception.ResourceNotFoundException;
import com.quangduong.SE114backend.repository.sql.UserRepository;
import com.quangduong.SE114backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BoardMapper {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private LabelMapper labelMapper;

    public BoardDTO toDTO(BoardEntity entity) {
        BoardDTO dto = new BoardDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAdminId(entity.getAdmin().getId());
        if(entity.getMembers().size() > 0)
            dto.setMembersIds(entity.getMembers().stream().map(m -> m.getId()).collect(Collectors.toList()));
        return dto;
    }

    public BoardDetailsDTO toDetailsDTO(BoardEntity entity) {
        BoardDetailsDTO dto = new BoardDetailsDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAdmin(userMapper.userInfoDTO(entity.getAdmin()));
        dto.setMembers(entity.getMembers().stream().map(m -> userMapper.userInfoDTO(m)).collect(Collectors.toList()));
        dto.setTables(entity.getTables().stream()
                        .filter(t -> securityUtils.getCurrentUserId() == entity.getAdmin().getId()
                                || t.getCreatedBy().equals(securityUtils.getCurrentUser().getEmail())
                                || t.getMembers().stream().anyMatch(m -> m.getId() == securityUtils.getCurrentUserId())
                        )
                        .map(t -> tableMapper.toDetailsDTO(t))
                        .collect(Collectors.toList())
        );
        dto.setLabels(entity.getLabels().stream().map(l -> labelMapper.toDTO(l)).toList());
        return dto;
    }

    public BoardEntity toEntity(BoardDTO dto) {
        BoardEntity entity = new BoardEntity();
        entity.setName(dto.getName());
        entity.setAdmin(securityUtils.getCurrentUser());
        return entity;
    }

    public BoardEntity toEntity(BoardUpdateDTO dto, BoardEntity entity) {
        if (dto.getName() != null)
            entity.setName(dto.getName());
        return entity;
    }
}
