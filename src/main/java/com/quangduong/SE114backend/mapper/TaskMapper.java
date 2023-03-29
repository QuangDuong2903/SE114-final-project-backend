package com.quangduong.SE114backend.mapper;

import com.quangduong.SE114backend.dto.task.TaskDTO;
import com.quangduong.SE114backend.entity.TableEntity;
import com.quangduong.SE114backend.entity.TaskEntity;
import com.quangduong.SE114backend.exception.NoPermissionException;
import com.quangduong.SE114backend.exception.ResourceNotFoundException;
import com.quangduong.SE114backend.repository.*;
import com.quangduong.SE114backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TaskMapper {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private TextAttributeMapper textAttributeMapper;

    @Autowired
    private TextAttributeRepository textAttributeRepository;

    @Autowired
    private NumberAttributeMapper numberAttributeMapper;

    @Autowired
    private NumberAttributeRepository numberAttributeRepository;

    @Autowired
    private DateAttributeMapper dateAttributeMapper;

    @Autowired
    private DateAttributeRepository dateAttributeRepository;

    @Autowired
    private LabelAttributeMapper labelAttributeMapper;

    @Autowired
    private LabelAttributeRepository labelAttributeRepository;

    public TaskEntity toEntity(TaskDTO dto) {
        TaskEntity entity = new TaskEntity();
        TableEntity tableEntity = tableRepository.findById(dto.getTableId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found table with id: " + dto.getTableId()));
        if (tableEntity.getBoard().getAdmin().getId() != dto.getUserId()
                && !tableEntity.getMembers().stream().anyMatch(m -> m.getId() == dto.getUserId()))
            throw new NoPermissionException("Not allowed");
        if(!dto.getLabelAttributes().stream()
                .allMatch(m ->
                        securityUtils.getCurrentUser().getLabels().stream().anyMatch(l -> l.getId() == m.getLabelId())))
            throw new NoPermissionException("Not allowed");
        entity.setUser(userRepository.findById(dto.getUserId()).
                orElseThrow(() -> new ResourceNotFoundException("Not found user with id: " + dto.getUserId()))
        );
        entity.setTable(tableEntity);
        if (dto.getTextAttributes() != null)
            entity.setTextAttributes(dto.getTextAttributes().stream()
                    .map(m -> textAttributeRepository.save(textAttributeMapper.toEntity(m)))
                    .collect(Collectors.toList())
            );
        if (dto.getNumberAttributes() != null)
            entity.setNumberAttributes(dto.getNumberAttributes().stream()
                    .map(m -> numberAttributeRepository.save(numberAttributeMapper.toEntity(m)))
                    .collect(Collectors.toList())
            );
        if (dto.getDateAttributes() != null)
            entity.setDateAttributes(dto.getDateAttributes().stream()
                    .map(m -> dateAttributeRepository.save(dateAttributeMapper.toEntity(m)))
                    .collect(Collectors.toList())
            );
        if (dto.getLabelAttributes() != null)
            entity.setLabelAttributes(dto.getLabelAttributes().stream()
                    .map(m -> labelAttributeRepository.save(labelAttributeMapper.toEntity(m)))
                    .collect(Collectors.toList())
            );
            return entity;
    }

    public TaskDTO toDTO(TaskEntity entity) {
        TaskDTO dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setTableId(entity.getTable().getId());
        dto.setUserId(entity.getUser().getId());
        dto.setTextAttributes(entity.getTextAttributes().stream()
                .map(m -> textAttributeMapper.toDTO(m))
                .collect(Collectors.toList())
        );
        dto.setNumberAttributes(entity.getNumberAttributes().stream()
                .map(m -> numberAttributeMapper.toDTO(m))
                .collect(Collectors.toList())
        );
        dto.setDateAttributes(entity.getDateAttributes().stream()
                .map(m -> dateAttributeMapper.toDTO(m))
                .collect(Collectors.toList())
        );
        dto.setLabelAttributes(entity.getLabelAttributes().stream()
                .map(m -> labelAttributeMapper.toDTO(m))
                .collect(Collectors.toList())
        );
        return dto;
    }
}
