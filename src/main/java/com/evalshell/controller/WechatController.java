package com.evalshell.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.evalshell.bean.model.OrderInfo;
import com.evalshell.bean.model.Setting;
import com.evalshell.service.PayMentService;
import com.evalshell.service.WeChatService;
import com.evalshell.service.impl.HomeDaoImpl;
import com.evalshell.service.impl.PayMentServiceImpl;
import com.evalshell.service.impl.WeChatServiceImpl;
import com.evalshell.utils.ResponseUtil;
import com.evalshell.utils.StringUtil;
import com.evalshell.utils.WeChatUtil;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.wxpay.sdk.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(value = "/wechat")
public class WechatController {
    @Autowired
    PayMentServiceImpl payMentService;

    @Autowired
    WeChatServiceImpl weChatService;

    @Autowired
    HomeDaoImpl homeDao;

    @Value("${wx.applet.appid}")
    private String  appid;
    @Value("${wx.applet.appsecert}")
    private String  appsecert;
    @Value("${wx.applet.much_id}")
    private String much_id;
    @Value("${wx.applet.much_key}")
    private String much_key;

    private String wxOAuth2CodeReturnUrl;

    @Value("${wx.applet.notifyUrl}")
    private String notifyUrl;

    private static final String TRADE_TYPE = "JSAPI";

    //获取授权码
    @GetMapping("/getWXOAuth2Code")
    public String getWXOAuth2Code(HttpServletRequest request, HttpServletResponse response){

        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
        String url = String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect",
                appid, wxOAuth2CodeReturnUrl
        );

        return "redirect:"+url;

    }

    @GetMapping("/wx-oauth-code-return")
    public String wxOAuth2CodeReturn(@RequestParam String code,@RequestParam String state){
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                appid, appsecert, code
        );
        //申请openid，请求url
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //申请openid接口响应的内容，其中包括了openid
        String body = exchange.getBody();
        log.info("申请openid响应的内容:{}",body);
        //获取openid
        String openid = JSON.parseObject(body).getString("openid");
        return openid;
    }

    private OrderInfo generOrder(Integer user_id, String type){
        //获取配置项
        Setting setting = homeDao.getSetting();
        JSONObject payinfo = setting.getPayinfobj();

        //创建订单
        String pay_no = StringUtil.generatePayNo();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPay_no(pay_no);
        orderInfo.setType(type);
        orderInfo.setUser_id(user_id);
        Integer money = JSONObject.parseObject(JSONObject.parseObject(payinfo.getString("viptype")).getString(type)).getInteger("now");
        orderInfo.setMoney(money);
        payMentService.createOrder(orderInfo);
        return orderInfo;
    }



    //统一下单，接收openid
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED) //开启事务操作
    @GetMapping("/wxjspay")
    public Object wxjspay(HttpServletRequest request,HttpServletResponse response) throws Exception {
        OrderInfo orderInfo = this.generOrder(Integer.valueOf(request.getParameter("user_id")), request.getParameter("type"));

        //创建sdk客户端
        WXPay wxPay = new WXPay(new WXPayConfigCustom());
        //构造请求的参数
        Map<String,String> requestParam = new HashMap<>();
        requestParam.put("out_trade_no",orderInfo.getPay_no());//订单号
        requestParam.put("body", "会员充值");//订单描述
        requestParam.put("fee_type", "CNY");//人民币
        requestParam.put("total_fee", String.valueOf(orderInfo.getMoney() * 100)); //金额
        requestParam.put("spbill_create_ip", request.getRemoteAddr());//客户端ip
        requestParam.put("notify_url", notifyUrl);//微信异步通知支付结果接口，暂时不用
        requestParam.put("trade_type", "JSAPI");
        //从请求中获取openid
        String openid = request.getParameter("openid");
        requestParam.put("openid",openid);
        //调用统一下单接口
        Map<String, String> resp = wxPay.unifiedOrder(requestParam);

        //准备h5网页需要的数据
        Map<String,String> jsapiPayParam = new HashMap<>();
        jsapiPayParam.put("appId",appid);
        jsapiPayParam.put("timeStamp",System.currentTimeMillis()/1000+"");
        jsapiPayParam.put("nonceStr", StringUtil.getRandomString(7));//随机字符串
        jsapiPayParam.put("package","prepay_id="+resp.get("prepay_id"));
        jsapiPayParam.put("signType","MD5");
        jsapiPayParam.put("paySign", WXPayUtil.generateSignature(jsapiPayParam, much_key, WXPayConstants.SignType.MD5));
        //将h5网页响应给前端
        return ResponseUtil.make_response(jsapiPayParam, "success", 200);
    }


    @RequestMapping(value = "/notify", method = {RequestMethod.POST}, produces = {"application/xml;charset=UTF-8"})
    @ResponseBody
    public Object Notify(HttpServletRequest request){
        InputStream is = null;
        String xmlBack = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line+"\n");
            }
            xmlBack = weChatService.notify(stringBuilder.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return xmlBack;
    }


    class  WXPayConfigCustom implements WXPayConfig {
        @Override
        public String getAppID() {
            return appid;
        }

        @Override
        public String getMchID() {
            return much_id;
        }

        @Override
        public String getKey() {
            return much_key;
        }

        @Override
        public InputStream getCertStream() {
            return null;
        }

        @Override
        public int getHttpConnectTimeoutMs() {
            return 0;
        }

        @Override
        public int getHttpReadTimeoutMs() {
            return 0;
        }
    }

}

