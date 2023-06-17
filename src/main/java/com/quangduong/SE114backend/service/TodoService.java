package com.quangduong.SE114backend.service;

import com.quangduong.SE114backend.dto.todo.TodoDTO;
import com.quangduong.SE114backend.dto.todo.TodoUpdateDTO;

import java.util.List;

public interface TodoService {

    List<TodoDTO> getAllTodos();

    TodoDTO createTodo(TodoDTO dto);

    TodoDTO updateTodo(TodoUpdateDTO dto);

    void deleteTodo(long id);
}
