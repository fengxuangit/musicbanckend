package com.evalshell.bean.serializer;

import com.evalshell.bean.model.Song;
import lombok.Data;

import java.util.Date;

@Data
public class FavouriteListSerializer {
    private Integer id;
    private Integer user_id;
    private Song song;
    private Date insert_tm;
    private Date update_tm;
}
