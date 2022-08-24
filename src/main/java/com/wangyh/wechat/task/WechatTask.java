package com.wangyh.wechat.task;

import com.wangyh.wechat.service.sendService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * 微信定时任务
 */
@Configuration
@EnableScheduling
public class WechatTask {

    @Resource
    private sendService sendService;

    /**
     * 微信模板消息推送
     */
    @Scheduled(cron="0 0 9 * * ? ")
    public void sendLoveMsg(){
        sendService.sendLoveMsg();
    }

    @Scheduled(cron = "0 0 12,14,16,18,20,22,23 * * ? ")
    public void sendTianGouMsg(){
        sendService.sendTianGouMsg();
    }

}
