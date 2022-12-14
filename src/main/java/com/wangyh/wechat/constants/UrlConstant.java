package com.wangyh.wechat.constants;

/**
 * URL常量
 */
public class UrlConstant {

    //获取accessToken
    public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&";

    //微信推送模板消息
    public final static String SEND_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    //获取关注用户信息
    public final static String GET_USER_LIST = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=";

    //彩虹屁接口
    public final static String CAI_HONG_API = "http://api.tianapi.com/caihongpi/index?key=";

    //早安接口
    public final static String MORNING_API = "http://api.tianapi.com/zaoan/index?key=";

    //天气接口
    public final static String TIAN_QI_API = "http://api.tianapi.com/tianqi/index?key=";

    //舔狗日记接口
    public final static String TIANGOU_API = "http://api.tianapi.com/tiangou/index?key=";
}
