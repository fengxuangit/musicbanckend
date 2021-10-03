package com.evalshell.service;


import java.util.Map;


public interface WeChatService {
    Map<String, Object> getOpenidByCode(String code);

    /**
     * @Description: 订单支付异步通知
     * @param notifyStr: 微信异步通知消息字符串
     * @Author:
     * @Date: 2019/8/1
     * @return
     */
    String notify(String notifyStr) throws Exception;
}
