package com.quangduong.SE114backend.repository.sql;

import com.quangduong.SE114backend.constant.UserStatus;
import com.quangduong.SE114backend.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByEmailAndStatus(String email, UserStatus status);
    List<UserEntity> findByEmailContainingOrDisplayNameContaining(String email, String name, Pageable pageable);
}
