package com.evalshell.bean.serializer;

import com.evalshell.bean.model.Home;
import com.evalshell.bean.model.Song;
import lombok.Data;

@Data
public class SongResult {
    private Integer status;
    private Home home;
}
