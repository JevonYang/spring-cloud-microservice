package com.yang;

import com.yang.dao.AuthorityDao;
import com.yang.dao.RoleDao;
import com.yang.dao.UserDao;
import com.yang.entity.Authority;
import com.yang.entity.Role;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AuthorityDao authorityDao;

    @org.junit.Test
    public void mybatisTest(){
        List<Role> roles = roleDao.selectRolesByUsername("yang");
        for (Role role:roles) {
            System.out.println(role.getName());
        }

        List<Authority> authorities = authorityDao.findAuthoritiesByRole(roles);

        for (Authority item: authorities) {
            System.out.println(item.getName());
        }

        System.out.println(new BCryptPasswordEncoder().encode("12345678"));

    }
}
