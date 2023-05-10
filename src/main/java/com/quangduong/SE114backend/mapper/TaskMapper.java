package com.quangduong.SE114backend.mapper;

import com.quangduong.SE114backend.dto.attribute.DateAttributeDTO;
import com.quangduong.SE114backend.dto.attribute.LabelAttributeDTO;
import com.quangduong.SE114backend.dto.attribute.NumberAttributeDTO;
import com.quangduong.SE114backend.dto.attribute.TextAttributeDTO;
import com.quangduong.SE114backend.dto.task.TaskDTO;
import com.quangduong.SE114backend.dto.task.TaskDetailsDTO;
import com.quangduong.SE114backend.dto.task.TaskUpdateDTO;
import com.quangduong.SE114backend.entity.*;
import com.quangduong.SE114backend.exception.NoPermissionException;
import com.quangduong.SE114backend.exception.ResourceNotFoundException;
import com.quangduong.SE114backend.repository.sql.*;
import com.quangduong.SE114backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
    private TaskRepository taskRepository;

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
    private UserMapper userMapper;

    @Autowired
    private LabelAttributeRepository labelAttributeRepository;

    public TaskEntity toEntity(TaskDTO dto) {
        TaskEntity entity = new TaskEntity();
        TableEntity tableEntity = tableRepository.findById(dto.getTableId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found table with id: " + dto.getTableId()));
        if (tableEntity.getBoard().getAdmin().getId() != dto.getUserId()
                && !tableEntity.getCreatedBy().equals(securityUtils.getCurrentUser().getEmail())
                && !tableEntity.getMembers().stream().anyMatch(m -> m.getId() == dto.getUserId()))
            throw new NoPermissionException("Not allowed");
        if (dto.getLabelAttributes() != null && !dto.getLabelAttributes().stream()
                .allMatch(m ->
                        securityUtils.getCurrentUser().getLabels().stream().anyMatch(l -> l.getId() == m.getLabelId())))
            throw new NoPermissionException("Not allowed");
        entity.setUser(userRepository.findById(dto.getUserId()).
                orElseThrow(() -> new ResourceNotFoundException("Not found user with id: " + dto.getUserId()))
        );
        entity.setTable(tableEntity);
        entity.setStatus(dto.getStatus());
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

    public TaskEntity toEntity(TaskUpdateDTO dto, TaskEntity entity) {
        if (entity.getTable().getBoard().getAdmin().getId() != securityUtils.getCurrentUserId()
                && !entity.getTable().getCreatedBy().equals(securityUtils.getCurrentUser().getEmail())
                && entity.getTable().getMembers().stream().noneMatch(m -> m.getId() == dto.getUserId()))
            throw new NoPermissionException("Not allowed");
        if (dto.getUserId() != null)
            if (entity.getTable().getMembers().stream().noneMatch(m -> m.getId() == dto.getUserId())
                    && !entity.getCreatedBy().equals(securityUtils.getCurrentUser().getEmail()))
                throw new NoPermissionException("Not allowed");
            else
                entity.setUser(userRepository.findById(dto.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("Not found user with id: " + dto.getUserId())));
        if (dto.getStatus() != null)
            entity.setStatus(dto.getStatus());
        if (dto.getTextAttributes() != null) {
            List<Long> ids = new ArrayList<>();
            for (TextAttributeEntity textAttributeEntity : entity.getTextAttributes()) {
                boolean isFound = false;
                for (TextAttributeDTO textAttributeDTO : dto.getTextAttributes()) {
                    if (textAttributeDTO.getId() != null && textAttributeEntity.getId() == textAttributeDTO.getId()) {
                        isFound = true;
                        textAttributeRepository.save(textAttributeMapper.toEntity(textAttributeDTO, textAttributeEntity));
                        break;
                    }
                }
                if(!isFound)
                    ids.add(textAttributeEntity.getId());
            }

            entity.setTextAttributes(entity.getTextAttributes().stream().filter(
                    t -> ids.stream().noneMatch(i -> i == t.getId())
            ).collect(Collectors.toList()));

            dto.getTextAttributes().forEach(t -> {
                if (t.getId() == null) {
                    TextAttributeEntity newTextAttributeEntity = textAttributeRepository.save(textAttributeMapper.toEntity(t));
                    newTextAttributeEntity.setTask(entity);
                    entity.getTextAttributes().add(newTextAttributeEntity);
                }
            });
        }

        if (dto.getNumberAttributes() != null) {
            List<Long> ids = new ArrayList<>();
            for (NumberAttributeEntity numberAttributeEntity : entity.getNumberAttributes()) {
                boolean isFound = false;
                for (NumberAttributeDTO numberAttributeDTO : dto.getNumberAttributes()) {
                    if (numberAttributeDTO.getId() != null && numberAttributeEntity.getId() == numberAttributeDTO.getId()) {
                        isFound = true;
                        numberAttributeRepository.save(numberAttributeMapper.toEntity(numberAttributeDTO, numberAttributeEntity));
                        break;
                    }
                }
                if(!isFound)
                    ids.add(numberAttributeEntity.getId());
            }

            entity.setNumberAttributes(entity.getNumberAttributes().stream().filter(
                    t -> ids.stream().noneMatch(i -> i == t.getId())
            ).collect(Collectors.toList()));

            dto.getNumberAttributes().forEach(t -> {
                if (t.getId() == null) {
                    NumberAttributeEntity newNumberAttributeEntity = numberAttributeRepository.save(numberAttributeMapper.toEntity(t));
                    newNumberAttributeEntity.setTask(entity);
                    entity.getNumberAttributes().add(newNumberAttributeEntity);
                }
            });
        }

        if (dto.getDateAttributes() != null) {
            List<Long> ids = new ArrayList<>();
            for (DateAttributeEntity dateAttributeEntity : entity.getDateAttributes()) {
                boolean isFound = false;
                for (DateAttributeDTO dateAttributeDTO : dto.getDateAttributes()) {
                    if (dateAttributeDTO.getId() != null && dateAttributeEntity.getId() == dateAttributeDTO.getId()) {
                        isFound = true;
                        dateAttributeRepository.save(dateAttributeMapper.toEntity(dateAttributeDTO, dateAttributeEntity));
                        break;
                    }
                }
                if(!isFound)
                    ids.add(dateAttributeEntity.getId());
            }

            entity.setDateAttributes(entity.getDateAttributes().stream().filter(
                    t -> ids.stream().noneMatch(i -> i == t.getId())
            ).collect(Collectors.toList()));

            dto.getDateAttributes().forEach(t -> {
                if (t.getId() == null) {
                    DateAttributeEntity newDateAttributeEntity = dateAttributeRepository.save(dateAttributeMapper.toEntity(t));
                    newDateAttributeEntity.setTask(entity);
                    entity.getDateAttributes().add(newDateAttributeEntity);
                }
            });
        }

        if (dto.getLabelAttributes() != null) {
            List<Long> ids = new ArrayList<>();
            for (LabelAttributeEntity labelAttributeEntity : entity.getLabelAttributes()) {
                boolean isFound = false;
                for (LabelAttributeDTO labelAttributeDTO : dto.getLabelAttributes()) {
                    if (labelAttributeDTO.getId() != null && labelAttributeEntity.getId() == labelAttributeDTO.getId()) {
                        isFound = true;
                        labelAttributeRepository.save(labelAttributeMapper.toEntity(labelAttributeDTO, labelAttributeEntity));
                        break;
                    }
                }
                if(!isFound)
                    ids.add(labelAttributeEntity.getId());
            }

            entity.setLabelAttributes(entity.getLabelAttributes().stream().filter(
                    t -> ids.stream().noneMatch(i -> i == t.getId())
            ).collect(Collectors.toList()));

            dto.getLabelAttributes().forEach(t -> {
                if (t.getId() == null) {
                    LabelAttributeEntity newlabelAttributeEntity = labelAttributeRepository.save(labelAttributeMapper.toEntity(t));
                    newlabelAttributeEntity.setTask(entity);
                    entity.getLabelAttributes().add(newlabelAttributeEntity);
                }
            });
        }

        return entity;
    }

    public TaskDetailsDTO toDetailsDTO(TaskEntity entity) {
        TaskDetailsDTO dto = new TaskDetailsDTO();
        dto.setId(entity.getId());
        dto.setUser(userMapper.userInfoDTO(entity.getUser()));
        dto.setStatus(entity.getStatus());
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

    public TaskDTO toDTO(TaskEntity entity) {
        TaskDTO dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setTableId(entity.getTable().getId());
        dto.setUserId(entity.getUser().getId());
        dto.setStatus(entity.getStatus());
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
