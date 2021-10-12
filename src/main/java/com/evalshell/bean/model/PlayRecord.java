package com.evalshell.bean.model;


import lombok.Data;

import java.util.Date;

@Data
public class PlayRecord {
    private Integer id;
    private Integer user_id;
    private Integer song_id;
    private Date insert_tm;
    private Date update_tm;
}
