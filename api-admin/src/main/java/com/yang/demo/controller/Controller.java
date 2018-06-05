package com.yang.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class Controller {

    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    RoleRepository roleRepository;

    @Value("${from}")
    private String from;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("from")
    public String from() {
        return from;
    }

    @RequestMapping("/hello")
    public String hello () {

        System.out.println("hello");
        return "hello, world";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
    public JSONArray userList(){
        //System.out.println("The following info is user list:\n");
        //System.out.println(user);
        List<UserEntity> users = userRepository.findAll();
        JSONArray userStr = (JSONArray) JSON.toJSON(users);
        return userStr;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserEntity User(@PathVariable("id") Long id) {
        UserEntity user = userRepository.getOne(id);
        return user;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    //@RequestBody
    public String addUser(@RequestBody String userString) {

        JSONObject userJson = new JSONObject();
        UserEntity user = JSON.parseObject(userString, UserEntity.class);
        user.setCreatedate(new Date());
        user.setName("Trump");
        userRepository.save(user);
        return "redirect:/list";
    }


}