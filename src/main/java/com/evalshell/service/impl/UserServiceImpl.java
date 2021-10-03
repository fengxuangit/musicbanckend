package com.evalshell.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.evalshell.bean.model.PlayRecord;
import com.evalshell.bean.model.User;
import com.evalshell.service.UserService;
import com.evalshell.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserService userService;


    @Override
    public Integer create(User user) {
        Date date = new Date();
        user.setInsert_tm(date);
        user.setUpdate_tm(date);
        return userService.create(user);
    }

    @Override
    public boolean delete(Integer userid) {
        return false;
    }

    @Override
    public User update(User user) {
        return userService.update(user);
    }

    @Override
    public User findUserById(Integer id) {
        return userService.findUserById(id);
    }

    @Override
    public User findUserByOpenId(String openid) {
        return userService.findUserByOpenId(openid);
    }

    @Override
    public List<PlayRecord> getPlayRecordListByUserId(Integer userid) {
        return userService.getPlayRecordListByUserId(userid);
    }
}
