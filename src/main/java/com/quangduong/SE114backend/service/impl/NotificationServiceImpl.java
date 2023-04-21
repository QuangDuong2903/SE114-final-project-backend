package com.quangduong.SE114backend.service.impl;

import com.quangduong.SE114backend.constant.NotificationType;
import com.quangduong.SE114backend.dto.notification.NotificationDTO;
import com.quangduong.SE114backend.entity.BoardEntity;
import com.quangduong.SE114backend.entity.NotificationEntity;
import com.quangduong.SE114backend.exception.NoPermissionException;
import com.quangduong.SE114backend.exception.ResourceNotFoundException;
import com.quangduong.SE114backend.mapper.NotificationMapper;
import com.quangduong.SE114backend.repository.sql.BoardRepository;
import com.quangduong.SE114backend.repository.sql.NotificationRepository;
import com.quangduong.SE114backend.repository.sql.UserRepository;
import com.quangduong.SE114backend.service.NotificationService;
import com.quangduong.SE114backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    public List<NotificationDTO> getNotifications() {
        return userRepository.findById(securityUtils.getCurrentUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with id: " + securityUtils.getCurrentUserId()))
                .getNotifications().stream()
                .sorted((o1, o2) -> o2.getCreatedDate().compareTo(o1.getCreatedDate()))
                .map(n -> notificationMapper.toDTO(n))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<NotificationDTO> setRead(List<Long> ids) {
        return ids.stream().map(i -> {
            NotificationEntity entity = notificationRepository.findById(i)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found notification with id: " + i));
            entity.setRead(true);
            return notificationMapper.toDTO(notificationRepository.save(entity));
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NotificationDTO acceptInvitation(long id) {
        NotificationEntity entity = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found notification with id: " + id));
        if (entity.getType() != NotificationType.INVITATION)
            throw new NoPermissionException("Not allowed");
        BoardEntity boardEntity = boardRepository.findById(entity.getBoardId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + entity.getBoardId()));
        if (boardEntity.getMembers().stream().filter(u -> u.getId() == securityUtils.getCurrentUserId()).findAny().isPresent())
            throw new RuntimeException("User already in board");
        if (!entity.isAccept()) {
            boardEntity.getMembers().add(securityUtils.getCurrentUser());
            boardRepository.save(boardEntity);
            entity.setAccept(true);
        }
        return notificationMapper.toDTO(notificationRepository.save(entity));
    }
}
