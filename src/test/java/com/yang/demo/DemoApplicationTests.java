package com.yang.demo;

import com.yang.demo.entity.DepartmentEntity;
import com.yang.demo.entity.RoleEntity;
import com.yang.demo.entity.UserEntity;
import com.yang.demo.repository.DepartmentRepository;
import com.yang.demo.repository.RoleRepository;
import com.yang.demo.repository.UserRepository;
//import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;


import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class DemoApplicationTests {

	private static Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

	@Autowired
    UserRepository userRepository;
	@Autowired
    DepartmentRepository departmentRepository;
	@Autowired
    RoleRepository roleRepository;

	@Before
    public void initData() {
	    userRepository.deleteAll();
	    roleRepository.deleteAll();
	    departmentRepository.deleteAll();

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName("kaifabu");
        departmentRepository.save(departmentEntity);
        org.springframework.util.Assert.notNull(departmentEntity.getId());

        RoleEntity roleEntity =new RoleEntity();
        roleEntity.setName("admin");
        roleRepository.save(roleEntity);
        Assert.notNull(roleEntity.getId());

        UserEntity user = new UserEntity();
        user.setName("user");
        user.setCreatedate(new Date());
        user.setDepartmentEntity(departmentEntity);

        List<RoleEntity> roles = roleRepository.findAll();
        Assert.notNull(roles);
        user.setRoles(roles);

        userRepository.save(user);
        Assert.notNull(user.getId());

    }

    @Test
    public void findPage(){
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
        Page<UserEntity> page = userRepository.findAll(pageable);
        Assert.notNull(page);
        for (UserEntity user: page.getContent()){
            logger.info("====user==== user name:{}, department name:{}, role name:{}", user.getName(),
                    user.getDepartmentEntity().getName(), user.getRoles().get(0).getName());
        }
    }
/*
    @Test
    public void test() {
        UserEntity user1 = userRepository.findByNameLike("u%");
        Assert.notNull(user1);

        UserEntity user2 =userRepository.readByName("user");
        Assert.notNull(user2);

        List<UserEntity> users = userRepository.getByCreatedateLessThan(new Date());
        Assert.notNull(users);



    }
*/

}
