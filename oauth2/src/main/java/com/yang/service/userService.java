package com.yang.service;

import java.util.List;

import com.yang.entity.User;

public interface userService{

    int insert(User User);

    int insertSelective(User User);

    int insertList(List<User> Users);

    int update(User User);
}
