package com.evalshell.bean.model;

import lombok.Data;

@Data
public class Category {
    private int id;
    private String name;
    private int parent_category_id;
}
