package com.evalshell.service;

import com.evalshell.bean.model.PlayRecord;
import com.evalshell.bean.model.User;
import lombok.extern.java.Log;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Integer create(User user);

    boolean delete(Integer userid);

    public User update(User user);

    public User findUserById(Integer id);

    public User findUserByOpenId(String openid);

    List<PlayRecord> getPlayRecordListByUserId(Integer userid);

}
