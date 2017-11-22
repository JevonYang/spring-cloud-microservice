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

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
    public JSONArray userList(){
        List<UserEntity> users = userRepository.findAll();
        JSONArray userStr = (JSONArray) JSON.toJSON(users);
        return userStr;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserEntity User(@PathVariable("id") Long id) {
        UserEntity user = userRepository.getOne(id);
        return user;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "text/plain")
    public String addUser(@RequestBody UserEntity user){

        System.out.println(user);
        user.setCreatedate(new Date());
        System.out.println(user.getName());
        System.out.println(user.getRoles().getName());
        System.out.println(user.getDepartmentEntity().getName());
        userRepository.save(user);
        return "redirect:/list";
    }


}