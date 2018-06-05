package com.yang.dao;

import com.yang.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Mapper
public interface RoleDao {
    int insert(@Param("Role") Role Role);

    int insertSelective(@Param("Role") Role Role);

    int insertList(@Param("Roles") List<Role> Roles);

    int update(@Param("Role") Role Role);

    List<Role> selectRolesByUsername(@Param("username") String username);
}
