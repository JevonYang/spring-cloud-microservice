package com.yang.service.imp;

import com.yang.entity.Authority;
import com.yang.service.authorityService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.yang.dao.AuthorityDao;

@Service
public class authorityServiceImpl implements authorityService {

    @Resource
    private AuthorityDao AuthorityDao;

    @Override
    public int insert(Authority Authority){
        return AuthorityDao.insert(Authority);
    }

    @Override
    public int insertSelective(Authority Authority){
        return AuthorityDao.insertSelective(Authority);
    }

    @Override
    public int insertList(List<Authority> Authorities){
        return AuthorityDao.insertList(Authorities);
    }

    @Override
    public int update(Authority Authority){
        return AuthorityDao.update(Authority);
    }
}
