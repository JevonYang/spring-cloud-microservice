package com.yang.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yang.demo.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

}
