package com.yang.service;

import java.util.List;

import com.yang.entity.Role;

public interface roleService{

    int insert(Role Role);

    int insertSelective(Role Role);

    int insertList(List<Role> Roles);

    int update(Role Role);
}
