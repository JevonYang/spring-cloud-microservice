package com.yang.service;

import java.util.List;

import com.yang.entity.Authority;

public interface authorityService{

    int insert(Authority Authority);

    int insertSelective(Authority Authority);

    int insertList(List<Authority> Authorities);

    int update(Authority Authority);
}
