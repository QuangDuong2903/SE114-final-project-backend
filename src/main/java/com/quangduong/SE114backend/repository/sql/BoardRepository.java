package com.quangduong.SE114backend.repository.sql;

import com.quangduong.SE114backend.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
