package com.evalshell.controller;

import com.evalshell.bean.model.Category;
import com.evalshell.bean.serializer.CategorySerializer;
import com.evalshell.service.HomeService;
import com.evalshell.service.impl.HomeDaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class CategoryController {

    @Autowired
    HomeDaoImpl homeService;

    @RequestMapping(value = "/categorys")
    public List<CategorySerializer> categorys(){
        List<Category> categories =  homeService.getAllCategory();
        List<CategorySerializer> categorySerializers = new ArrayList<CategorySerializer>();
        for (Category category: categories){
            if (category.getParent_category_id() == 0){
                CategorySerializer categorySerializer = new CategorySerializer();
                categorySerializer.setId(category.getId());
                categorySerializer.setName(category.getName());
                List<Category> categories1 = new ArrayList<Category>();
                categorySerializer.setChild(categories1);

                categorySerializers.add(categorySerializer);
            }
        }
        for (Category category: categories){
            for (CategorySerializer categorySerializer: categorySerializers){
                if (categorySerializer.getId() == category.getParent_category_id()){
                    categorySerializer.getChild().add(category);
                }
            }

        }

        return categorySerializers;
    }
}
