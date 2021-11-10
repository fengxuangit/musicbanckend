package com.evalshell.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.evalshell.bean.model.Home;
import com.evalshell.bean.model.OrderInfo;
import com.evalshell.bean.model.Setting;
import com.evalshell.bean.model.User;
import com.evalshell.service.WeChatService;
import com.evalshell.utils.CommonUtil;
import com.evalshell.utils.HttpUtil;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Service
public class WeChatServiceImpl implements WeChatService {

    @Value("${wx.applet.appid}")
    private String appid;
    @Value("${wx.applet.appsecert}")
    private String appsecert;

    @Autowired
    PayMentServiceImpl payMentService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    WeChatService weChatService;

    @Override
    public Map<String, Object> getOpenidByCode(String code) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        Map<String, String> querys = new HashMap<>();
        querys.put("appid", appid);
        querys.put("secret", appsecert);
        querys.put("js_code", code);
        querys.put("grant_type", "authorization_code");
        HttpResponse httpResponse = null;
        try{
            httpResponse = HttpUtil.doGet("https://api.weixin.qq.com", "/sns/jscode2session", null, headers, querys);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        JSONObject jsonObject = null;
        try {
            InputStream stream = httpResponse.getEntity().getContent();
            byte[] buffer = new byte[1024];
            int len;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((len = stream.read(buffer)) > -1 ) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.close();
            stream.close();
            String str= new String(byteArrayOutputStream.toByteArray(),"UTF-8");
            System.out.println(str);
            jsonObject = JSONObject.parseObject(str);
            System.out.println(jsonObject);
        }catch (Exception exception){
            exception.printStackTrace();
        }

        return jsonObject;
    }
//
    private Integer vipexpiretime(String type){
        if (type.equals("month")){
           return 1;
        }
        if (type.equals("quarter")){
            return 3;
        }
        if (type.equals("half")){
            return 6;
        }
        if (type.equals("year")){
            return 12;
        }
        return 1;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED) //开启事务操作
    @Override
    public String notify(String notifyStr) throws Exception {
        String xmlBack = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
        try {
            // 转换成map
            Map<String, String> resultMap = WXPayUtil.xmlToMap(notifyStr);
            String returnCode = resultMap.get("return_code");  //状态
            if (!returnCode.equals("SUCCESS")){
                return xmlBack;
            }
            String outTradeNo = resultMap.get("out_trade_no");//商户订单号
            //更新订单相关
            OrderInfo order = payMentService.getOrderByPayNo(outTradeNo);
            if (order == null){
                return xmlBack;
            }
            if (order.getStatus() == 1){
                xmlBack = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[订单无法重复提交]]></return_msg></xml> ";
                return xmlBack;
            }
            order.setStatus(1);
            payMentService.updateOrder(order);

            //更新用户相关
            User user = userService.findUserById(order.getUser_id());
            //获取这个人需要加几个月的时间
            Integer timenumber = this.vipexpiretime(order.getType());
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINA);
            Date newdate = null;
            System.out.println(user);
            if (user.getIsvip() == 1){
                newdate = CommonUtil.getAfterMonth(dateFormat.format(user.getVip_expiretime()), timenumber);
            }else{
                newdate = CommonUtil.getAfterMonth(dateFormat.format(new Date()), timenumber);
                user.setIsvip(1);
            }
            user.setVip_expiretime(newdate);
            System.out.println(user);
            userService.update(user);
            xmlBack = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlBack;
    }
}
