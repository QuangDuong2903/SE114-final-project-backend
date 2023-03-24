package com.quangduong.SE114backend.repository;

import com.quangduong.SE114backend.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
