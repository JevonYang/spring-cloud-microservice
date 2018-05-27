package com.yang.MicroserviceInfo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yang.MicroserviceInfo.entity.MicroService;
import com.yang.MicroserviceInfo.repository.MicroServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/microservice")
public class MicroServiceController {

    @Autowired
    MicroServiceRepository microServiceRepository;

    private String prefix="localhost:8080/microservice/";

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
    public JSONArray microServiceList(){
        List<MicroService> microServices = microServiceRepository.findAll();
        JSONArray microServicesJson = (JSONArray) JSON.toJSON(microServices);
        return microServicesJson;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addMicroService(@RequestBody String microServiceStr) {
        MicroService microService = JSON.parseObject(microServiceStr, MicroService.class);
        microService.setCreateDate(new Date());
        microServiceRepository.save(microService);
        return "redirect/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MicroService microService(@PathVariable("id") Long id) {
        MicroService microService = microServiceRepository.getOne(id);
        return microService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public MicroService reviseMicroService(@PathVariable("id") Long id, @RequestBody String microServiceStr) {
        MicroService microService = microServiceRepository.getOne(id);
        MicroService input = JSON.parseObject(microServiceStr, MicroService.class);
        microService.setCreateDate(new Date());
        microService.setCreator(input.getCreator());
        microService.setDescription(input.getDescription());
        microService.setName(input.getName());
        return microService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delMicroService(@PathVariable("id") Long id) {
        microServiceRepository.delete(id);
        return "redirect/list";
    }

}
