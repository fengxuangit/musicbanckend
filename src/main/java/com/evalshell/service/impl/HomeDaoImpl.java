package com.evalshell.service.impl;

import com.alibaba.fastjson.JSON;
import com.evalshell.bean.model.*;
import com.evalshell.bean.serializer.FavouriteListSerializer;
import com.evalshell.bean.serializer.HomeRecommendBase;
import com.evalshell.bean.serializer.HomeRecommendSerializer;
import com.evalshell.service.HomeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class HomeDaoImpl implements HomeService {

    @Autowired
    HomeService homeService;

    @Override
    public List<Home> findAll() {
        return homeService.findAll();
    }

    @Override
    public List<HomeRecommendSerializer> getFormatHomeRecommend() {
        List<HomeRecommend> homeRecommends = homeService.getHomeRecommend();
        List<HomeRecommendSerializer> homeRecommendSerializerList = new ArrayList<HomeRecommendSerializer>();

        for (HomeRecommend homeRecommend : homeRecommends){
            String recommend_name = homeRecommend.getRecommend_name();
            boolean flag = false;
            HomeRecommendBase homeRecommendBase = new HomeRecommendBase();
            homeRecommendBase.setId(homeRecommend.getId());
            homeRecommendBase.setSong_id(homeRecommend.getSong_id());
            homeRecommendBase.setSong_id(homeRecommend.getSong_id());
            homeRecommendBase.setImage_url(homeRecommend.getImage_url());
            homeRecommendBase.setIsvip(homeRecommend.getIsvip());
            homeRecommendBase.setDescribe(homeRecommend.getDescribe());
            homeRecommendBase.setName(homeRecommend.getName());
            for (HomeRecommendSerializer hrs: homeRecommendSerializerList){
                if (recommend_name.equals(hrs.getName())){
                    List<HomeRecommendBase> data = hrs.getData();
                    data.add(homeRecommendBase);
                    flag = true;
                    break;
                }
            }
            if (flag != true){
                HomeRecommendSerializer homeRecommendSerializer = new HomeRecommendSerializer();
                homeRecommendSerializer.setName(homeRecommend.getRecommend_name());
                List<HomeRecommendBase> data = new ArrayList<HomeRecommendBase>();
                data.add(homeRecommendBase);

                homeRecommendSerializer.setData(data);

                homeRecommendSerializerList.add(homeRecommendSerializer);
            }

        }
        return homeRecommendSerializerList;
    }

    @Override
    public List<HomeRecommend> getHomeRecommendByName(String name) {
        return homeService.getHomeRecommendByName(name);
    }

    @Override
    public List<HomeRecommend> getCategoryRecommendByName(String name) {
        return homeService.getCategoryRecommendByName(name);
    }

    @Override
    public List<HomeRecommend> getHomeRecommendById(Integer id) {
        return homeService.getHomeRecommendById(id);
    }

    @Override
    public List<RecommendCategory> getRecommendCategory() {
        return homeService.getRecommendCategory();
    }

    @Override
    public List<Category> getAllCategory() {
        return homeService.getAllCategory();
    }

    @Override
    public List<Song> getSongByCategoryId(Integer category_id) {
        return homeService.getSongByCategoryId(category_id);
    }

    @Override
    public List<HomeRecommend> getHomeRecommend() {
        return homeService.getHomeRecommend();
    }

    @Override
    public void favouriteCreate(Favourite favourite) {
        Date date = new Date();
        favourite.setInsert_tm(date);
        favourite.setUpdate_tm(date);
        homeService.favouriteCreate(favourite);
    }

    @Override
    public void delFavourite(Integer user_id, Integer song_id) {
        homeService.delFavourite(user_id, song_id);
    }

    @Override
    public List<FavouriteListSerializer> getFavouriteListByUserId(Integer user_id) {
        return homeService.getFavouriteListByUserId(user_id);
    }

    @Override
    public Setting getSetting() {
        Setting setting = homeService.getSetting();
        setting.setPayinfobj(JSON.parseObject(setting.getPayinfo()));
        return setting;
    }

    @Override
    public Category getCategoryById(Integer category_id) {
        return homeService.getCategoryById(category_id);
    }

    @Override
    public List<Song> getSongByParentCategoryId(Integer category_id) {
        return homeService.getSongByParentCategoryId(category_id);
    }

    @Override
    public List<Song> searchSong(String name) {
        return homeService.searchSong(name);
    }

    @Override
    public Home getSongById(Integer id) {
        return homeService.getSongById(id);
    }

    @Override
    public void addPlayRecord(PlayRecord playRecord) {
        Date date = new Date();
        playRecord.setInsert_tm(date);
        playRecord.setUpdate_tm(date);
        homeService.addPlayRecord(playRecord);
    }

    @Override
    public Integer findFavouriteById(Integer user_id, Integer song_id) {
        return homeService.findFavouriteById(user_id, song_id);
    }

    @Override
    public void createImage(Image image) {
        homeService.createImage(image);
    }
}
