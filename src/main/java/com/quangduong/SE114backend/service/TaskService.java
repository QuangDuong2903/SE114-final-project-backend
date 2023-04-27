package com.quangduong.SE114backend.service;

import com.quangduong.SE114backend.dto.task.TaskDTO;
import com.quangduong.SE114backend.dto.task.TaskDetailsDTO;
import com.quangduong.SE114backend.dto.task.TaskUpdateDTO;

public interface TaskService {

    TaskDetailsDTO createTask(TaskDTO dto);
    TaskDetailsDTO updateTask(TaskUpdateDTO dto);
    void deleteTask(long id);

}
