package com.yang.demo.controller;

import com.yang.demo.entity.UserEntity;
import com.yang.demo.repository.UserRepository;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/hello")
    public String hello () {
        return "hello, world";
    }

    @RequestMapping("/hello1")
    public String hello1 () {
        return "hello, world!";
    }

}