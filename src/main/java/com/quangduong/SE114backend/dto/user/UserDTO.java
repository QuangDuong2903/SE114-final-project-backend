package com.quangduong.SE114backend.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class UserDTO {

    private long id;
    @NotBlank(message = "Email required")
    @Email(message = "Email not valid")
    private String email;

    @NotBlank(message = "Display name required")
    private String displayName;

    @NotBlank(message = "Given name required")
    private String givenName;

    @NotBlank(message = "Family name required")
    private String familyName;

    @NotBlank(message = "Photo required")
    private String photoUrl;

    private List<UserBoardDTO> boards;

    public List<UserBoardDTO> getBoards() {
        return boards;
    }

    public void setBoards(List<UserBoardDTO> boards) {
        this.boards = boards;
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

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
