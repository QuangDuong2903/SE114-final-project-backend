package com.quangduong.SE114backend.repository.sql;

import com.quangduong.SE114backend.constant.NotificationType;
import com.quangduong.SE114backend.entity.NotificationEntity;
import com.quangduong.SE114backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    NotificationEntity findOneByTypeAndBoardIdAndUserAndIsAcceptAndIsReject(NotificationType type, long boardId, UserEntity user, boolean isAccept, boolean isReject);
}
