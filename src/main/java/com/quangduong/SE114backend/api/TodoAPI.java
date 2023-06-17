package com.quangduong.SE114backend.api;

import com.quangduong.SE114backend.dto.todo.TodoDTO;
import com.quangduong.SE114backend.dto.todo.TodoUpdateDTO;
import com.quangduong.SE114backend.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("todos")
public class TodoAPI {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
            return ResponseEntity.ok(todoService.getAllTodos());
    }

    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(@RequestBody @Valid TodoDTO dto) {
        return new ResponseEntity<>(todoService.createTodo(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable("id") long id, @RequestBody TodoUpdateDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(todoService.updateTodo(dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok().build();
    }

}
