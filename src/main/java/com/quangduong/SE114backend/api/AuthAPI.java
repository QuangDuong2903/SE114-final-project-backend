package com.quangduong.SE114backend.api;

import com.quangduong.SE114backend.dto.user.UserBoardDTO;
import com.quangduong.SE114backend.dto.user.UserDTO;
import com.quangduong.SE114backend.service.UserService;
import com.quangduong.SE114backend.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid UserDTO dto) {
        UserDTO user = userService.createUser(dto);
        return ResponseEntity.ok(new LoginResponse(user.getId(),
                user.getEmail(),
                user.getDisplayName(),
                user.getPhotoUrl(),
                jwtUtils.generateToken(user.getEmail()),
                user.getBoards())
        );
    }

    record LoginResponse(long id, String email, String displayName, String photoUrl, String token, List<UserBoardDTO> boards) {}

}
