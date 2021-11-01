package com.evalshell.service;

import com.evalshell.bean.model.*;
import com.evalshell.bean.serializer.FavouriteListSerializer;
import com.evalshell.bean.serializer.HomeRecommendSerializer;

import java.util.Date;
import java.util.List;

public interface HomeService {

    public List<Home> findAll();

    public List<HomeRecommend> getHomeRecommend();

    public List<HomeRecommendSerializer> getFormatHomeRecommend();

    public List<HomeRecommend> getHomeRecommendByName(String name);

    public List<HomeRecommend> getHomeRecommendById(Integer id);

    public List<HomeRecommend> getCategoryRecommendByName(String name);

    public List<RecommendCategory> getRecommendCategory();

    public List<Category> getAllCategory();

    public List<Song> getSongByCategoryId(Integer category_id);

    void favouriteCreate(Favourite favourite);

    void delFavourite(Integer user_id, Integer song_id);

    Integer findFavouriteById(Integer user_id, Integer song_id);

    List<FavouriteListSerializer> getFavouriteListByUserId(Integer user_id);

    Setting getSetting();

    Category getCategoryById(Integer category_id);

    List<Song> getSongByParentCategoryId(Integer category_id);

    List<Song> searchSong(String name);

    Home getSongById(Integer id);

    void createImage(Image image);

    void addPlayRecord(PlayRecord playRecord);
}
