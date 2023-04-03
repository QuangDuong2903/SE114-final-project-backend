package com.quangduong.SE114backend.repository.sql;

import com.quangduong.SE114backend.constant.UserStatus;
import com.quangduong.SE114backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByEmailAndStatus(String email, UserStatus status);
}
