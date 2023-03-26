package com.quangduong.SE114backend.service.impl;

import com.quangduong.SE114backend.constant.UserStatus;
import com.quangduong.SE114backend.dto.user.UserDTO;
import com.quangduong.SE114backend.entity.UserEntity;
import com.quangduong.SE114backend.mapper.UserMapper;
import com.quangduong.SE114backend.repository.UserRepository;
import com.quangduong.SE114backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO dto) {
        UserEntity user = userRepository.findOneByEmailAndStatus(dto.getEmail(), UserStatus.ACTIVE);
        if(user != null)
            return userMapper.toDTO(user);
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(dto)));
    }
}
