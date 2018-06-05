package com.yang.dao;

import com.yang.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.yang.entity.Authority;

@Mapper
public interface AuthorityDao {
    int insert(@Param("Authority") Authority Authority);

    int insertSelective(@Param("Authority") Authority Authority);

    int insertList(@Param("Authorities") List<Authority> Authorities);

    int update(@Param("Authority") Authority Authority);

    List<Authority> findAuthoritiesByRole(@Param("Role") List<Role> roles);
}
