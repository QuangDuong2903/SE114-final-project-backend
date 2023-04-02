package com.quangduong.SE114backend.service;

import com.quangduong.SE114backend.dto.task.TaskDTO;
import com.quangduong.SE114backend.dto.task.TaskUpdateDTO;

public interface TaskService {

    TaskDTO createTask(TaskDTO dto);
    TaskDTO updateTask(TaskUpdateDTO dto);
    void deleteTask(long id);

}
