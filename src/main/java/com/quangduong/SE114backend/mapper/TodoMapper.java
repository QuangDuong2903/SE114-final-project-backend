package com.quangduong.SE114backend.mapper;

import com.quangduong.SE114backend.dto.todo.TodoDTO;
import com.quangduong.SE114backend.dto.todo.TodoUpdateDTO;
import com.quangduong.SE114backend.entity.TodoEntity;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public TodoEntity toEntity(TodoDTO dto) {
        TodoEntity entity = new TodoEntity();
        entity.setTitle(dto.getTitle());
        entity.setDone(dto.isDone());
        return entity;
    }

    public TodoEntity toEntity(TodoEntity entity, TodoUpdateDTO dto) {
        if (dto.getTitle() != null)
            entity.setTitle(dto.getTitle());
        if (dto.isDone() != null)
            entity.setDone(dto.isDone());
        return entity;
    }

    public TodoDTO toDTO(TodoEntity entity) {
        TodoDTO dto = new TodoDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDone(entity.isDone());
        return dto;
    }

}
