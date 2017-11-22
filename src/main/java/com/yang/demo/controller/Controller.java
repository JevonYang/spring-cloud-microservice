package com.yang.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yang.demo.entity.DepartmentEntity;
import com.yang.demo.entity.RoleEntity;
import com.yang.demo.entity.UserEntity;
import com.yang.demo.repository.DepartmentRepository;
import com.yang.demo.repository.RoleRepository;
import com.yang.demo.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class Controller {

    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/hello")
    public String hello () {

        System.out.println("hello");
        return "hello, world";
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces="application/json")
    public UserEntity User (@PathVariable("name") String name) {
        System.out.println("hello");

        UserEntity user1 = new UserEntity();
        user1.setName(name);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName("开发组");
        departmentRepository.save(departmentEntity);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("Admin");
        roleRepository.save(roleEntity);

        user1.setCreatedate(new Date());
        user1.setDepartmentEntity(departmentEntity);
        user1.setRoles(roleEntity);
        userRepository.save(user1);

        return user1;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public JSONArray userList(){
        List<UserEntity> users = userRepository.findAll();
        JSONArray userStr = (JSONArray) JSON.toJSON(users);
        return userStr;
    }

}