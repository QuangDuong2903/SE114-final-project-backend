package com.quangduong.SE114backend.entity;

import com.quangduong.SE114backend.constant.NotificationType;
import jakarta.persistence.*;

@Entity
@Table(name = "notification")
public class NotificationEntity extends BaseEntity {

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "message")
    private String message;

    @Column(name = "is_read")
    private boolean isRead;

    @Column(name = "is_accept")
    private boolean isAccept;

    @Column(name = "is_reject")
    private boolean isReject;

    @Column(name = "type")
    private NotificationType type;

    @Column(name = "board_id")
    private long boardId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isAccept() {
        return isAccept;
    }

    public void setAccept(boolean accept) {
        isAccept = accept;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public boolean isReject() {
        return isReject;
    }

    public void setReject(boolean reject) {
        isReject = reject;
    }

    public NotificationEntity() {}

    public NotificationEntity(String thumbnail, String message, boolean isRead, boolean isAccept, boolean isReject, NotificationType type, long boardId, UserEntity user) {
        this.thumbnail = thumbnail;
        this.message = message;
        this.isRead = isRead;
        this.isAccept = isAccept;
        this.isReject = isReject;
        this.type = type;
        this.boardId = boardId;
        this.user = user;
    }
}
