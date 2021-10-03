package com.evalshell.controller;

import com.evalshell.bean.model.Home;
import com.evalshell.bean.model.PlayRecord;
import com.evalshell.bean.model.User;
import com.evalshell.bean.serializer.FavouriteListSerializer;
import com.evalshell.service.HomeService;
import com.evalshell.service.UserService;
import com.evalshell.service.impl.UserServiceImpl;
import com.evalshell.service.impl.WeChatServiceImpl;
import com.evalshell.utils.ResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    HomeService homeService;

    @Autowired
    UserService userService;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    WeChatServiceImpl WeChatServiceImpl;

    @RequestMapping(value = "/test")
    public Object test(@RequestBody String body){
        List<Home> homeList = homeService.findAll();
        return  ResponseUtil.make_response(homeList, "success", 201);
    }

    @RequestMapping(value = "/login")
    public Object login(@RequestParam("code") String  code){
        Map<String, Object> object = WeChatServiceImpl.getOpenidByCode(code);
        if (object == null){
            return ResponseUtil.make_response(null, "没有此用户", 400);
        }
        String openid = (String) object.get("openid");
        User user = userServiceImpl.findUserByOpenId(openid);
        return ResponseUtil.make_response(user, "success", 201);
    }


    @RequestMapping(value = "/create")
    public Object create(@RequestBody User user){
        userServiceImpl.create(user);
        return ResponseUtil.make_response(user, "success", 201);
    }

    @RequestMapping(value = "/playrecord")
    public Object playrecord(@RequestParam("user_id") Integer user_id, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageHelper.startPage(page, size);
        List<PlayRecord> playRecords = userService.getPlayRecordListByUserId(user_id);
        PageInfo<PlayRecord> pageInfo = new PageInfo<>(playRecords);
        return pageInfo;
    }
}
