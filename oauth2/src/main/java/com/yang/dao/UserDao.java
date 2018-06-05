package com.yang.dao;

import com.yang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserDao {
    int insert(@Param("User") User User);

    int insertSelective(@Param("User") User User);

    int insertList(@Param("Users") List<User> Users);

    int update(@Param("User") User User);

    User findOneByUsername(@Param("username")String username);


}
