package com.yang.service.imp;

import com.yang.entity.User;
import com.yang.service.userService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.yang.dao.UserDao;

@Service
public class userServiceImpl implements userService {

    @Resource
    private UserDao UserDao;

    @Override
    public int insert(User User){
        return UserDao.insert(User);
    }

    @Override
    public int insertSelective(User User){
        return UserDao.insertSelective(User);
    }

    @Override
    public int insertList(List<User> Users){
        return UserDao.insertList(Users);
    }

    @Override
    public int update(User User){
        return UserDao.update(User);
    }
}
