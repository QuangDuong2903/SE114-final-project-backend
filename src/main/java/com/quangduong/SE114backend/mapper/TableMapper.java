package com.quangduong.SE114backend.mapper;

import com.quangduong.SE114backend.dto.table.TableDTO;
import com.quangduong.SE114backend.dto.table.TableUpdateDTO;
import com.quangduong.SE114backend.entity.TableEntity;
import com.quangduong.SE114backend.exception.ResourceNotFoundException;
import com.quangduong.SE114backend.repository.sql.BoardRepository;
import com.quangduong.SE114backend.repository.sql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TableMapper {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public TableDTO toDTO(TableEntity entity) {
        TableDTO dto = new TableDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBoardId(entity.getBoard().getId());
        dto.setMemberIds(entity.getMembers().stream().map(m -> m.getId()).collect(Collectors.toList()));
        dto.setTaskIds(entity.getTasks().stream().map(t -> t.getId()).collect(Collectors.toList()));
        return dto;
    }

    public TableEntity toEntity(TableDTO dto) {
        TableEntity entity = new TableEntity();
        entity.setName(dto.getName());
        entity.setBoard(boardRepository.findById(dto.getBoardId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + dto.getBoardId()))
        );
        if(dto.getMemberIds() != null)
            entity.setMembers(dto.getMemberIds().stream()
                    .map(i -> userRepository.findById(i)
                            .orElseThrow(() -> new ResourceNotFoundException("Not found user with id: " + i)))
                    .collect(Collectors.toList())
            );
        return entity;
    }

    public TableEntity toEntity(TableUpdateDTO dto, TableEntity entity) {
        if(dto.getName() != null)
            entity.setName(dto.getName());
        if(dto.getMemberIds() != null)
            entity.setMembers(dto.getMemberIds().stream()
                    .map(i -> userRepository.findById(i)
                            .orElseThrow(() -> new ResourceNotFoundException("Not found user with id: " + i)))
                    .collect(Collectors.toList())
            );
        return entity;
    }

}
