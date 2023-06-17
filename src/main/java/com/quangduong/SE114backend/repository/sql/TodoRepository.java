package com.quangduong.SE114backend.repository.sql;

import com.quangduong.SE114backend.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
