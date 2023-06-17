package com.quangduong.SE114backend.service.impl;

import com.quangduong.SE114backend.dto.todo.TodoDTO;
import com.quangduong.SE114backend.dto.todo.TodoUpdateDTO;
import com.quangduong.SE114backend.entity.TodoEntity;
import com.quangduong.SE114backend.exception.ResourceNotFoundException;
import com.quangduong.SE114backend.mapper.TodoMapper;
import com.quangduong.SE114backend.repository.sql.TodoRepository;
import com.quangduong.SE114backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoMapper todoMapper;

    @Override
    public List<TodoDTO> getAllTodos() {
        return todoRepository.findAll().stream().map(todoMapper::toDTO).toList();
    }

    @Override
    @Transactional
    public TodoDTO createTodo(TodoDTO dto) {
        return todoMapper.toDTO(todoRepository.save(todoMapper.toEntity(dto)));
    }

    @Override
    @Transactional
    public TodoDTO updateTodo(TodoUpdateDTO dto) {
        TodoEntity entity = todoRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found todo with id: " + dto.getId()));
        return todoMapper.toDTO(todoRepository.save(todoMapper.toEntity(entity, dto)));
    }

    @Override
    @Transactional
    public void deleteTodo(long id) {
        todoRepository.deleteById(id);
    }
}
