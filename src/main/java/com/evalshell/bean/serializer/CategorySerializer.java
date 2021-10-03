package com.evalshell.bean.serializer;

import com.evalshell.bean.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategorySerializer {
    private int id;
    private String name;
    private List<Category> child;
}
