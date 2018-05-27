package com.yang.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yang.demo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
