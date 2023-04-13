package com.quangduong.SE114backend.service;

import com.quangduong.SE114backend.dto.user.UserDTO;
import com.quangduong.SE114backend.dto.user.UserInfoDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    public UserDTO createUser(UserDTO dto);
    List<UserInfoDTO> findUser(String keyword, Pageable pageable);
}
