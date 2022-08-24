package com.wangyh.wechat.service.impl;

import cn.hutool.json.JSONObject;
import com.wangyh.wechat.config.TianApiConfig;
import com.wangyh.wechat.config.WechatConfig;
import com.wangyh.wechat.domain.model.WeatherInfo;
import com.wangyh.wechat.domain.vo.WechatSendMsgVo;
import com.wangyh.wechat.domain.vo.WechatTemplateVo;
import com.wangyh.wechat.service.WeiXinService;
import com.wangyh.wechat.service.sendService;
import com.wangyh.wechat.utils.util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class sendServiceImpl implements sendService {

    @Resource
    private WeiXinService weiXinService;

    @Resource
    private WechatConfig wechatConfig;

    @Resource
    private TianApiConfig tianApiConfig;


    @Override
    public void sendLoveMsg() {
        //配置及数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String appId = wechatConfig.getAppId();
        String appSecret = wechatConfig.getAppSecret();
        String babyBirthday = wechatConfig.getBabyBirthday();
        String myBirthday = wechatConfig.getMyBirthday();
        String loveDay = wechatConfig.getLoveDay();
        String appKey = tianApiConfig.getAppKey();
        String area = tianApiConfig.getArea();
        //获取微信token
        String token = weiXinService.getAccessToken(appId,appSecret);
        //获取关注用户
        List<String> userList = weiXinService.getUserList(token);

        //获取早安语句
        String zaoAnInfo = weiXinService.getZaoAnInfo(appKey);
        //获取天气
        WeatherInfo weatherInfo = weiXinService.getWeatherInfo(appKey, area);
        //获取彩虹屁
        String caiHongPiInfo = weiXinService.getCaiHongPiInfo(appKey);

        for (String openId : userList) {
            //发送消息实体
            WechatSendMsgVo sendMsgVo = new WechatSendMsgVo();
            //设置模板id
            sendMsgVo.setTemplate_id(wechatConfig.getTempId());
            //设置接收用户
            sendMsgVo.setTouser(openId);
            Map<String, WechatTemplateVo> map = new HashMap<>();
            //早安语句
            map.put("morning", new WechatTemplateVo("Baby 早安！"+zaoAnInfo,"#ff6666"));
            //天气信息
            //日期
            map.put("date", new WechatTemplateVo(weatherInfo.getDate(),null));
            //星期
            map.put("week",new WechatTemplateVo(weatherInfo.getWeek(),null));
            //城市
            map.put("city",new WechatTemplateVo(weatherInfo.getArea(),"#9900ff"));
            //天气
            map.put("weather",new WechatTemplateVo(weatherInfo.getWeather(),"#CD96CD"));
            //最低气温
            map.put("lowest",new WechatTemplateVo(weatherInfo.getLowest(),"#A4D3EE"));
            //最高气温
            map.put("highest",new WechatTemplateVo(weatherInfo.getHighest(),"#CD3333"));
            //降水概率
            map.put("pop",new WechatTemplateVo(weatherInfo.getPop()+"%","#A4D3EE"));
            //今日建议
            map.put("tips",new WechatTemplateVo(weatherInfo.getTips(),"#FF7F24"));
            //相爱天数
            int loveDays = util.fun(loveDay, date);
            map.put("loveDay",new WechatTemplateVo(loveDays+"","#EE6AA7"));
            //我的生日
            int myDay = util.fun2(myBirthday);
            map.put("myBirthday",new WechatTemplateVo(myDay+"","#EE6AA7"));
            //宝贝生日
            int babyDay = util.fun2(babyBirthday);
            map.put("babyBirthday",new WechatTemplateVo(babyDay+"","#EE6AA7"));
            //彩虹屁
            map.put("pipi",new WechatTemplateVo(caiHongPiInfo,"#E066FF"));

            sendMsgVo.setData(map);
            JSONObject entries = weiXinService.sendMsg(sendMsgVo,token, openId);
        }
    }

    @Override
    public void sendTianGouMsg() {
        //配置及数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String appId = wechatConfig.getAppId();
        String appSecret = wechatConfig.getAppSecret();
        String appKey = tianApiConfig.getAppKey();
        String tiangouDay = wechatConfig.getTiangouDay();
        //获取微信token
        String token = weiXinService.getAccessToken(appId, appSecret);
        //获取关注用户
        List<String> userList = weiXinService.getUserList(token);
        //获取舔狗日记
        String tianGou = weiXinService.getTianGou(appKey);

        for (String openId : userList) {
            //发送消息实体
            WechatSendMsgVo sendMsgVo = new WechatSendMsgVo();
            //设置舔狗日记模板id
            sendMsgVo.setTemplate_id(wechatConfig.getTiangouTempId());
            //设置接收用户
            sendMsgVo.setTouser(openId);
            Map<String, WechatTemplateVo> map = new HashMap<>();
            //设置舔狗日记
            map.put("tianGou", new WechatTemplateVo(tianGou, "#FF7F24"));
            int loveDays = util.fun(tiangouDay, date);
            map.put("loveDay",new WechatTemplateVo(loveDays+"","#EE6AA7"));
            sendMsgVo.setData(map);
            JSONObject entries = weiXinService.sendMsg(sendMsgVo, token, openId);
        }
    }
}
