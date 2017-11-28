package com.yang.APIInfo.repository;

import com.yang.APIInfo.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<Api, Long> {
}
