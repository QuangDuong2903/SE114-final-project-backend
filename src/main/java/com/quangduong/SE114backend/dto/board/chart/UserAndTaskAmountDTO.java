package com.quangduong.SE114backend.dto.board.chart;

public class UserAndTaskAmountDTO {

    private long id;

    private String email;

    private String displayName;

    private String photoUrl;

    private int amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public UserAndTaskAmountDTO(long id, String email, String displayName, String photoUrl, int amount) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.photoUrl = photoUrl;
        this.amount = amount;
    }
}
