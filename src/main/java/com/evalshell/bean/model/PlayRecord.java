package com.evalshell.bean.model;


import lombok.Data;

@Data
public class PlayRecord {
    private Integer id;
    private Integer user_id;
    private Song song;
}
