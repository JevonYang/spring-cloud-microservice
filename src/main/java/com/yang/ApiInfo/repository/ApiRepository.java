package com.yang.ApiInfo.repository;

import com.yang.ApiInfo.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<Api, Long> {
}
