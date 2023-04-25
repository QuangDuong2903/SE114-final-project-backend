package com.quangduong.SE114backend.api;

import com.quangduong.SE114backend.dto.notification.NotificationDTO;
import com.quangduong.SE114backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationAPI {

    @Autowired
    private NotificationService notificationService;

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

}
