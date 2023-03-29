package com.quangduong.SE114backend.service.impl;

import com.quangduong.SE114backend.dto.task.TaskDTO;
import com.quangduong.SE114backend.entity.TaskEntity;
import com.quangduong.SE114backend.mapper.TaskMapper;
import com.quangduong.SE114backend.repository.*;
import com.quangduong.SE114backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

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
    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }
}
