package com.quangduong.SE114backend.repository.sql;

import com.quangduong.SE114backend.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
