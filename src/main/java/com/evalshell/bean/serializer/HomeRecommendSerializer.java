package com.evalshell.bean.serializer;

import lombok.Data;

import java.util.List;

@Data
public class HomeRecommendSerializer {
    private String name;
    private List<HomeRecommendBase> data;
}
