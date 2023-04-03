package com.quangduong.SE114backend.repository.sql;

import com.quangduong.SE114backend.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
}
