package com.quangduong.SE114backend.service;

import com.quangduong.SE114backend.dto.task.TaskDTO;

public interface TaskService {

    TaskDTO createTask(TaskDTO dto);
    void deleteTask(long id);

}
