package com.evalshell.bean.serializer;

import lombok.Data;

@Data
public class HomeRecommendBase {
    private int id;
    private int song_id;
    private Integer isvip;
    private String image_url;
    private String name;
    private String describe;
}
