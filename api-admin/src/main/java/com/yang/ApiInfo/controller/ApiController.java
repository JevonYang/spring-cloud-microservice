package com.yang.ApiInfo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yang.ApiInfo.entity.Api;
import com.yang.ApiInfo.repository.ApiRepository;
import com.yang.MicroserviceInfo.entity.MicroService;
import com.yang.MicroserviceInfo.repository.MicroServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    ApiRepository apiRepository;

    @Autowired
    MicroServiceRepository microServiceRepository;

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
    public JSONArray apiList(){
        List<Api> apis = apiRepository.findAll();
        JSONArray apiJson = (JSONArray) JSON.toJSON(apis);
        return apiJson;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addApi(@RequestBody String apiStr) {
        Api api = JSON.parseObject(apiStr, Api.class);
        api.setUpdateDate(new Date());
        apiRepository.save(api);
        return "redirect/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Api api(@PathVariable("id") Long id) {
        Api api = apiRepository.getOne(id);
        return api;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Api reviseApi(@PathVariable("id") Long id, @RequestBody String apiJson) {
        Api api = apiRepository.getOne(id);
        Api input = JSON.parseObject(apiJson, Api.class);
        MicroService service = microServiceRepository.findOne(input.getMicroService().getId());
        api.setCreator(input.getCreator());
        api.setDescription(input.getDescription());
        api.setMicroService(service);
        api.setName(input.getStatus());
        api.setStatus(input.getStatus());
        api.setVersion(input.getVersion());
        api.setUpdateDate(new Date());
        apiRepository.save(api);
        return api;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delApi(@PathVariable("id") Long id) {
        apiRepository.delete(id);
        return  "redirect/list";
    }

}
