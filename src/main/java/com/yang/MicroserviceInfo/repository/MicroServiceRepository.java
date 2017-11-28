package com.yang.MicroserviceInfo.repository;

import com.yang.MicroserviceInfo.entity.MicroService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MicroServiceRepository extends JpaRepository<MicroService, Long> {


}
