package com.evalshell.bean.model;

import lombok.Data;

import java.util.Date;

@Data
public class Song {
    private int id;
    private String name;
    private Integer isvip;
    private String describe;
    private String key;
    private String url;
    private String song_key;
    private String song_url;
    private int category_id;
    private Date insert_tm;
    private Date update_tm;
}
