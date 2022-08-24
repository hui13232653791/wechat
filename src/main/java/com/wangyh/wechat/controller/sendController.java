package com.wangyh.wechat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class sendController {

    @Resource
    private com.wangyh.wechat.service.sendService sendService;

    @GetMapping("/send")
    public String sendLoveMsg(){
        sendService.sendLoveMsg();
        return "success";
    }

    @GetMapping("/sendTG")
    public String sendDrinkMsg(){
        sendService.sendTianGouMsg();
        return "success";
    }


}
