package com.evalshell.bean.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Calendar {
    private int id;
    private Image image;
    private Integer image_id;
    private String post_image;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date date;
    private String word;
    private JSONObject wordObject;
    private Date insert_tm;
    private Date update_tm;
}
