package com.wangyh.wechat.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信配置
 */
@Component
@Data
public class WechatConfig {

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    @Value("${wechat.tempId}")
    private String tempId;

    @Value("${wechat.myBirthday}")
    private String myBirthday;

    @Value("${wechat.babyBirthday}")
    private String babyBirthday;

    @Value("${wechat.loveDay}")
    private String loveDay;

    @Value("${wechat.tiangouTempId}")
    private String tiangouTempId;

    @Value("${wechat.tiangouDay}")
    private String tiangouDay;
}
