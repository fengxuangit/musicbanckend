package com.evalshell.bean.serializer;

import lombok.Data;

@Data
public class HomeRecommendBase {
    private int song_id;
    private String song_key;
    private String song_url;
    private String key;
    private String url;
    private String name;
    private String describe;
}
