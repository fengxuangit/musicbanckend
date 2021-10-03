package com.evalshell.bean.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderInfo {
    private Integer id;
    private Integer user_id;
    private String pay_no;
    private String type;
    private Integer money;
    private Integer status;
    private Date pay_tm;
    private Date insert_tm;
    private Date update_tm;
}
