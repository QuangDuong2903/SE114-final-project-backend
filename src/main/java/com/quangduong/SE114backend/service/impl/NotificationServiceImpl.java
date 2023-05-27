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
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
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

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

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
        if (entity.isAccept() || entity.isReject())
            throw new NoPermissionException("Notification already responded");
        BoardEntity boardEntity = boardRepository.findById(entity.getBoardId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + entity.getBoardId()));
        if (boardEntity.getAdmin().getId() == securityUtils.getCurrentUserId()
                || boardEntity.getMembers().stream().anyMatch(u -> u.getId() == securityUtils.getCurrentUserId()))
            throw new NoPermissionException("User already in board");
        boardEntity.getMembers().add(securityUtils.getCurrentUser());
        boardRepository.save(boardEntity);
        entity.setAccept(true);

        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setMessage(securityUtils.getCurrentUser().getDisplayName() + " accepted your invitation");
        notificationEntity.setRead(false);
        notificationEntity.setUser(boardEntity.getAdmin());
        notificationEntity.setThumbnail(securityUtils.getCurrentUser().getPhotoUrl());
        notificationEntity.setType(NotificationType.INFORMATION);
        messagingTemplate.convertAndSend("/notification/" + boardEntity.getAdmin().getId(), notificationMapper.toDTO(notificationRepository.save(notificationEntity)));
        return notificationMapper.toDTO(notificationRepository.save(entity));
    }

    @Override
    @Transactional
    public NotificationDTO rejectInvitation(long id) {
        NotificationEntity entity = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found notification with id: " + id));
        if (entity.getType() != NotificationType.INVITATION)
            throw new NoPermissionException("Not allowed");
        if (entity.isAccept() || entity.isReject())
            throw new NoPermissionException("Not allowed");
        entity.setReject(true);

        BoardEntity boardEntity = boardRepository.findById(entity.getBoardId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + entity.getBoardId()));
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setMessage(securityUtils.getCurrentUser().getDisplayName() + " rejected your invitation");
        notificationEntity.setRead(false);
        notificationEntity.setUser(boardEntity.getAdmin());
        notificationEntity.setThumbnail(securityUtils.getCurrentUser().getPhotoUrl());
        notificationEntity.setType(NotificationType.INFORMATION);
        messagingTemplate.convertAndSend("/notification/" + boardEntity.getAdmin().getId(), notificationMapper.toDTO(notificationRepository.save(notificationEntity)));
        return notificationMapper.toDTO(notificationRepository.save(entity));
    }
}
