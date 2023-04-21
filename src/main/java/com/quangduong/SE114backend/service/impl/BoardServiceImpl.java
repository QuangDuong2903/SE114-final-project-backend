package com.quangduong.SE114backend.service.impl;

import com.quangduong.SE114backend.constant.NotificationType;
import com.quangduong.SE114backend.dto.board.BoardDTO;
import com.quangduong.SE114backend.dto.board.BoardDetailsDTO;
import com.quangduong.SE114backend.dto.board.BoardUpdateDTO;
import com.quangduong.SE114backend.entity.BoardEntity;
import com.quangduong.SE114backend.entity.NotificationEntity;
import com.quangduong.SE114backend.exception.NoPermissionException;
import com.quangduong.SE114backend.exception.ResourceNotFoundException;
import com.quangduong.SE114backend.mapper.BoardMapper;
import com.quangduong.SE114backend.mapper.NotificationMapper;
import com.quangduong.SE114backend.repository.sql.BoardRepository;
import com.quangduong.SE114backend.repository.sql.NotificationRepository;
import com.quangduong.SE114backend.repository.sql.UserRepository;
import com.quangduong.SE114backend.service.BoardService;
import com.quangduong.SE114backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    public BoardDetailsDTO getBoardDetails(long id) {
        BoardEntity entity = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + id));
        if (entity.getAdmin().getId() != securityUtils.getCurrentUserId() && entity.getMembers().stream().noneMatch(m -> m.getId() == securityUtils.getCurrentUserId()))
            throw new NoPermissionException("Not allowed");
        return boardMapper.toDetailsDTO(entity);
    }

    @Override
    @Transactional
    public BoardDTO createBoard(BoardDTO dto) {
        if (dto.getMembersIds() != null)
            dto.getMembersIds().forEach(i -> userRepository.findById(i)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found user with id: " + i)));
        BoardEntity entity = boardRepository.save(boardMapper.toEntity(dto));
        if (dto.getMembersIds() != null)
            dto.getMembersIds().forEach(i -> {
                NotificationEntity notificationEntity = new NotificationEntity();
                notificationEntity.setAccept(false);
                notificationEntity.setMessage("You has been invited to " + entity.getName());
                notificationEntity.setRead(false);
                notificationEntity.setUser(userRepository.findById(i).get());
                notificationEntity.setThumbnail(securityUtils.getCurrentUser().getPhotoUrl());
                notificationEntity.setType(NotificationType.INVITATION);
                messagingTemplate.convertAndSend("/notification/" + i, notificationMapper.toDTO(notificationRepository.save(notificationEntity)));
            });
        return boardMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public BoardDTO updateBoard(BoardUpdateDTO dto) {
        long id = dto.getId();
        if (securityUtils.getCurrentUserId() != boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + id)).getAdmin().getId())
            throw new NoPermissionException("Update board with id: " + id + " not allowed");
        return boardMapper.toDTO(
                boardRepository.save(boardMapper.toEntity(dto, boardRepository.findById(dto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + dto.getId()))
                ))
        );
    }

    @Override
    @Transactional
    public void deleteBoardById(long id) {
        if (securityUtils.getCurrentUserId() != boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + id)).getId())
            throw new NoPermissionException("Delete board with id: " + id + " not allowed");
        boardRepository.deleteById(id);
    }
}
