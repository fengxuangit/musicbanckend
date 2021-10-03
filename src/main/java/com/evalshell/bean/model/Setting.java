package com.evalshell.bean.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class Setting {
    private Integer id;
    private String payinfo;
    private JSONObject payinfobj;

}
