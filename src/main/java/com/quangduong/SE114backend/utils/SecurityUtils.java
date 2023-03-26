package com.quangduong.SE114backend.utils;

import com.quangduong.SE114backend.entity.UserEntity;
import com.quangduong.SE114backend.repository.UserRepository;
import com.quangduong.SE114backend.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getCurrentUser() {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(user.getId()).get();
    }

    public long getCurrentUserId() {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

}
