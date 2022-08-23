package com.wechat.template.controller;

import com.wechat.template.task.WechatTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class sendController {

    @Autowired
    private WechatTask wechatTask;

    @GetMapping("/send")
    public void sendLoveMsg(){
        try {
            wechatTask.sendLoveMsg();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/sendTG")
    public void sendDrinkMsg(){
        try {
            wechatTask.sendTianGouMsg();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
