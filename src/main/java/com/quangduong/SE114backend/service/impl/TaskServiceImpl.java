package com.quangduong.SE114backend.service.impl;

import com.quangduong.SE114backend.dto.task.TaskDTO;
import com.quangduong.SE114backend.dto.task.TaskUpdateDTO;
import com.quangduong.SE114backend.entity.TableEntity;
import com.quangduong.SE114backend.entity.TaskEntity;
import com.quangduong.SE114backend.exception.NoPermissionException;
import com.quangduong.SE114backend.exception.ResourceNotFoundException;
import com.quangduong.SE114backend.mapper.TaskMapper;
import com.quangduong.SE114backend.repository.*;
import com.quangduong.SE114backend.service.TaskService;
import com.quangduong.SE114backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TextAttributeRepository textAttributeRepository;

    @Autowired
    private NumberAttributeRepository numberAttributeRepository;

    @Autowired
    private DateAttributeRepository dateAttributeRepository;

    @Autowired
    private LabelAttributeRepository labelAttributeRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskDTO createTask(TaskDTO dto) {
        TaskEntity entity = taskRepository.save(taskMapper.toEntity(dto));
        entity.getTextAttributes().forEach(m -> m.setTask(entity));
        entity.getNumberAttributes().forEach(m -> m.setTask(entity));
        entity.getDateAttributes().forEach(m -> m.setTask(entity));
        entity.getLabelAttributes().forEach(m -> m.setTask(entity));
        return taskMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public TaskDTO updateTask(TaskUpdateDTO dto) {
        TaskEntity entity = taskRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found task with id: " + dto.getId()));
        List<Long> textAttributeIds = entity.getTextAttributes().stream().map(t -> t.getId()).collect(Collectors.toList());
        List<Long> numberAttributeIds = entity.getNumberAttributes().stream().map(t -> t.getId()).collect(Collectors.toList());
        List<Long> dateAttributeIds = entity.getDateAttributes().stream().map(t -> t.getId()).collect(Collectors.toList());
        List<Long> labelAttributeIds = entity.getLabelAttributes().stream().map(t -> t.getId()).collect(Collectors.toList());
        entity = taskMapper.toEntity(dto, entity);
        TaskEntity finalEntity = entity;
        textAttributeIds.forEach(i -> {
            if(finalEntity.getTextAttributes().stream().noneMatch(t -> t.getId() == i))
                textAttributeRepository.deleteById(i);
        });
        numberAttributeIds.forEach(i -> {
            if(finalEntity.getNumberAttributes().stream().noneMatch(t -> t.getId() == i))
                numberAttributeRepository.deleteById(i);
        });
        dateAttributeIds.forEach(i -> {
            if(finalEntity.getDateAttributes().stream().noneMatch(t -> t.getId() == i))
                dateAttributeRepository.deleteById(i);
        });
        labelAttributeIds.forEach(i -> {
            if(finalEntity.getLabelAttributes().stream().noneMatch(t -> t.getId() == i))
                labelAttributeRepository.deleteById(i);
        });
        taskRepository.save(entity);
        return taskMapper.toDTO(taskRepository.findById(dto.getId()).get());
    }

    @Override
    @Transactional
    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }
}
