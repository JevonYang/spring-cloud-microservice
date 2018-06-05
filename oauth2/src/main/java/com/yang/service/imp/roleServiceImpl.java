package com.yang.service.imp;

import com.yang.entity.Role;
import com.yang.service.roleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.yang.dao.RoleDao;

@Service
public class roleServiceImpl implements roleService {

    @Resource
    private RoleDao RoleDao;

    @Override
    public int insert(Role Role){
        return RoleDao.insert(Role);
    }

    @Override
    public int insertSelective(Role Role){
        return RoleDao.insertSelective(Role);
    }

    @Override
    public int insertList(List<Role> Roles){
        return RoleDao.insertList(Roles);
    }

    @Override
    public int update(Role Role){
        return RoleDao.update(Role);
    }
}
