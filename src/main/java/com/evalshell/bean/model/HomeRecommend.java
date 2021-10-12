package com.evalshell.bean.model;

import lombok.Data;

@Data
public class HomeRecommend {
    private int song_id;
    private Integer isvip;
    private String image_url;
    private String recommend_name;
    private String name;
    private String describe;
}
