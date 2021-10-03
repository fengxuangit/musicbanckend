package com.evalshell.bean.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class Favourite {
    private int id;
    private int user_id;
    private int song_id;
    @TableField(fill = FieldFill.INSERT)
    private Date insert_tm;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_tm;
}
