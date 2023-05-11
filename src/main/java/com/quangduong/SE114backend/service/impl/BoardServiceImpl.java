package com.quangduong.SE114backend.service.impl;

import com.quangduong.SE114backend.constant.NotificationType;
import com.quangduong.SE114backend.constant.TaskStatus;
import com.quangduong.SE114backend.dto.board.BoardDTO;
import com.quangduong.SE114backend.dto.board.BoardDetailsDTO;
import com.quangduong.SE114backend.dto.board.BoardUpdateDTO;
import com.quangduong.SE114backend.dto.board.chart.ChartDTO;
import com.quangduong.SE114backend.dto.board.chart.UserAndTaskAmountDTO;
import com.quangduong.SE114backend.entity.BoardEntity;
import com.quangduong.SE114backend.entity.NotificationEntity;
import com.quangduong.SE114backend.entity.UserEntity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
        if (entity.getAdmin().getId() != securityUtils.getCurrentUserId()
                && entity.getMembers().stream().noneMatch(m -> m.getId() == securityUtils.getCurrentUserId()))
            throw new NoPermissionException("Not allowed");
        return boardMapper.toDetailsDTO(entity);
    }

    @Override
    public ChartDTO getChartData(long id) {
        BoardEntity entity = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + id));
        if (entity.getAdmin().getId() != securityUtils.getCurrentUserId()
                && entity.getMembers().stream().noneMatch(m -> m.getId() == securityUtils.getCurrentUserId()))
            throw new NoPermissionException("Not allowed");
        List<UserEntity> users = entity.getMembers();
        users.add(entity.getAdmin());
        ChartDTO dto = new ChartDTO();
        dto.setChart_1(users.stream().filter(u -> u.getTasks().stream().anyMatch(t -> t.getTable().getBoard().getId() == id && t.getStatus().equals(TaskStatus.DONE)))
                .map(u -> new UserAndTaskAmountDTO(
                                u.getId(),
                                u.getEmail(),
                                u.getDisplayName(),
                                u.getPhotoUrl(),
                                (int) u.getTasks().stream().filter(t -> t.getTable().getBoard().getId() == id && t.getStatus().equals(TaskStatus.DONE)).count()
                        )
                ).sorted((o1, o2) -> o2.getAmount() - o1.getAmount()).limit(5).toList());
        dto.setChart_2(users.stream().map(u -> new UserAndTaskAmountDTO(
                        u.getId(),
                        u.getEmail(),
                        u.getDisplayName(),
                        u.getPhotoUrl(),
                        (int) u.getTasks().stream().filter(t -> t.getTable().getBoard().getId() == id).count()
                )
        ).toList());
        List<UserAndTaskAmountDTO> data = new ArrayList<>();
        UserEntity user = securityUtils.getCurrentUser();
        data.add(new UserAndTaskAmountDTO(
                user.getId(),
                user.getEmail(),
                user.getDisplayName(),
                user.getPhotoUrl(),
                (int) user.getTasks().stream().filter(t -> t.getTable().getBoard().getId() == id && t.getStatus().equals(TaskStatus.DONE)).count()
        ));
        data.add(new UserAndTaskAmountDTO(
                user.getId(),
                user.getEmail(),
                user.getDisplayName(),
                user.getPhotoUrl(),
                (int) securityUtils.getCurrentUser().getTasks().stream().filter(t -> t.getTable().getBoard().getId() == id && t.getStatus().equals(TaskStatus.PENDING)).count()
        ));
        data.add(new UserAndTaskAmountDTO(
                user.getId(),
                user.getEmail(),
                user.getDisplayName(),
                user.getPhotoUrl(),
                (int) user.getTasks().stream().filter(t -> t.getTable().getBoard().getId() == id && t.getStatus().equals(TaskStatus.STUCK)).count()
        ));
        dto.setChart_3(data);
        return dto;
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
                notificationEntity.setReject(false);
                notificationEntity.setUser(userRepository.findById(i).get());
                notificationEntity.setThumbnail(securityUtils.getCurrentUser().getPhotoUrl());
                notificationEntity.setType(NotificationType.INVITATION);
                messagingTemplate.convertAndSend("/notification/" + i, notificationMapper.toDTO(notificationRepository.save(notificationEntity)));
            });
        return boardMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public BoardDetailsDTO updateBoard(BoardUpdateDTO dto) {
        long id = dto.getId();
        BoardEntity entity = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + id));
        if (securityUtils.getCurrentUserId() != boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + id)).getAdmin().getId())
            throw new NoPermissionException("Update board with id: " + id + " not allowed");
        if (dto.getMembersIds() != null) {
            if (dto.getMembersIds().stream().allMatch(i -> entity.getMembers().stream().anyMatch(m -> m.getId() == i))) {
                entity.setMembers(
                        dto.getMembersIds().stream()
                                .map(i -> userRepository.findById(i)
                                        .orElseThrow(() -> new ResourceNotFoundException("Not found user with id: " + id)))
                                .collect(Collectors.toList()));
                boardRepository.save(entity);
            } else {
                dto.getMembersIds().forEach(i -> {
                    NotificationEntity notificationEntity =
                            notificationRepository.findOneByTypeAndBoardIdAndUserAndIsAcceptAndIsReject(NotificationType.INVITATION, entity.getId(), userRepository.findById(i).get(), false, false);
                    if (notificationEntity == null) {
                        notificationEntity = new NotificationEntity();
                        notificationEntity.setAccept(false);
                        notificationEntity.setMessage("You has been invited to " + entity.getName());
                        notificationEntity.setRead(false);
                        notificationEntity.setReject(false);
                        notificationEntity.setBoardId(entity.getId());
                        notificationEntity.setUser(userRepository.findById(i).get());
                        notificationEntity.setThumbnail(securityUtils.getCurrentUser().getPhotoUrl());
                        notificationEntity.setType(NotificationType.INVITATION);
                        messagingTemplate.convertAndSend("/notification/" + i, notificationMapper.toDTO(notificationRepository.save(notificationEntity)));
                    }
                });
            }
        }
        return boardMapper.toDetailsDTO(
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
