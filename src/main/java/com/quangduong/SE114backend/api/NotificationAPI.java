package com.quangduong.SE114backend.api;

import com.quangduong.SE114backend.constant.NotificationType;
import com.quangduong.SE114backend.dto.notification.NotificationDTO;
import com.quangduong.SE114backend.entity.NotificationEntity;
import com.quangduong.SE114backend.mapper.NotificationMapper;
import com.quangduong.SE114backend.repository.sql.NotificationRepository;
import com.quangduong.SE114backend.service.NotificationService;
import com.quangduong.SE114backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NotificationAPI {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("notifications")
    public ResponseEntity<List<NotificationDTO>> getNotificationsOfUser() {
        return ResponseEntity.ok(notificationService.getNotifications());
    }

    @PutMapping("notifications")
    public ResponseEntity<List<NotificationDTO>> updateReadNotifications(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(notificationService.setRead(ids));
    }

    @PutMapping("invitations/{id}/accept")
    public ResponseEntity<NotificationDTO> acceptInvitation(@PathVariable("id") long id) {
        return ResponseEntity.ok(notificationService.acceptInvitation(id));
    }

    @PutMapping("invitations/{id}/reject")
    public ResponseEntity<NotificationDTO> rejectInvitation(@PathVariable("id") long id) {
        return ResponseEntity.ok(notificationService.rejectInvitation(id));
    }

    @PostMapping("notifications")
    public ResponseEntity<Void> createNotification() {
        NotificationDTO dto = new NotificationDTO();
        dto.setMessage("Ahihihih");
        messagingTemplate.convertAndSend("/notification/2", dto);
        return ResponseEntity.ok(null);
    }

    @PostMapping("messages")
    public ResponseEntity<Void> createMessage() {
        Message dto = new Message("board",
                "quangduongptsc@gmail.com",
                "Dinh Quang Duong",
                "https://lh3.googleusercontent.com/a/ALm5wu1FXdzUcXvVoXwQYpqRAr8Yy7RZFMXU6srYuyaW=s96-c",
                "SIUUUU",
                new Date()
        );
        messagingTemplate.convertAndSend("/chatroom/1", dto);
        return ResponseEntity.ok(null);
    }

    record Message(String boardName, String email, String displayName, String photoUrl, String message,
                   Date timestamp) {
    }
}
