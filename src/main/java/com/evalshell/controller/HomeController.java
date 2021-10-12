package com.evalshell.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.evalshell.bean.model.*;
import com.evalshell.bean.model.Calendar;
import com.evalshell.bean.serializer.FavouriteListSerializer;
import com.evalshell.bean.serializer.HomeRecommendBase;
import com.evalshell.bean.serializer.HomeRecommendSerializer;
import com.evalshell.service.CalendarService;
import com.evalshell.service.UserService;
import com.evalshell.service.impl.CalendarServiceImpl;
import com.evalshell.service.impl.HomeDaoImpl;
import com.evalshell.utils.CommonUtil;
import com.evalshell.utils.ResponseUtil;
import com.evalshell.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    HomeDaoImpl homeService;

    @Autowired
    UserService userService;

    @Autowired
    CalendarServiceImpl calendarService;


    @RequestMapping(value = "/gethome")
    public Object getHome(){
        List<Home> homeList = homeService.findAll();
        return ResponseUtil.make_response(homeList, "success", 200);
    }

    @RequestMapping(value = "/getCalendarByDate")
    public Calendar getCalendarByDate(@RequestParam("date") String date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = format.parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        Calendar calendar =  calendarService.findCalendarByDate(date1);
        calendar.setWordObject(JSONObject.parseObject(calendar.getWord()));
        return calendar;
    }


    @RequestMapping(value = "/getHomeRecommend")
    public Object getHomeRecommend(){
        return ResponseUtil.make_response(homeService.getFormatHomeRecommend(), "success", 200);
    }

    @RequestMapping(value = "/getRecommendCategory")
    public Object getRecommendCategory(){
        return ResponseUtil.make_response(homeService.getRecommendCategory(), "success", 200);
    }



    @RequestMapping(value = "/getHomeRecommendByName")
    public Object getHomeRecommendByNamePageInfo(@RequestParam("name") String name, @RequestParam(value = "page", defaultValue = "0") Integer page,@RequestParam(value = "size", defaultValue = "10") Integer size){
        PageHelper.startPage(page, size);
        List<HomeRecommend> hr = homeService.getHomeRecommendByName(name);
        PageInfo<HomeRecommend> pageInfo = new PageInfo<>(hr);
        return ResponseUtil.make_response(pageInfo, "success", 200);
    }

    @RequestMapping(value = "/getCategoryRecommendByName")
    public Object getCategoryRecommendByName(@RequestParam("name") String name, @RequestParam(value = "page", defaultValue = "0") Integer page,@RequestParam(value = "size", defaultValue = "10") Integer size){
        PageHelper.startPage(page, size);
        List<HomeRecommend> hr = homeService.getCategoryRecommendByName(name);
        PageInfo<HomeRecommend> pageInfo = new PageInfo<>(hr);
        return ResponseUtil.make_response(pageInfo, "success", 200);
    }

    @RequestMapping(value = "/search")
    public Object search(@RequestParam("name") String name, @RequestParam(value = "page", defaultValue = "0") Integer page,@RequestParam(value = "size", defaultValue = "10") Integer size){
        PageHelper.startPage(page, size);
        List<Song> hr = homeService.searchSong(name);
        System.out.println(hr);
        PageInfo<Song> pageInfo = new PageInfo<>(hr);
        return ResponseUtil.make_response(pageInfo, "success", 200);
    }


    @RequestMapping(value = "/getSongByCategoryId")
    public Object getSongByCategoryId(@RequestParam("id") Integer id, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageHelper.startPage(page, size);
        Category category = homeService.getCategoryById(id);
        List<Song> songs = null;
        System.out.println(category.getParent_category_id());
        if (category.getParent_category_id() == 0){
            songs = homeService.getSongByParentCategoryId(category.getId());
        }else{
            songs = homeService.getSongByCategoryId(id);
        }
        PageInfo<Song> pageInfo = new PageInfo<>(songs);
        return ResponseUtil.make_response(pageInfo, "success", 200);
    }

    @RequestMapping(value = "/calendar/{date}")
    public Object Daliy(@PathVariable("date") String date){
        System.out.println(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date date1 = null;
        try{
            date1 = simpleDateFormat.parse(date);
        }catch (ParseException e){
            e.printStackTrace();
            log.error("检查出错!");
            return ResponseUtil.make_response(null, "日期解析错误！", 100);
        }

        Calendar calendar = calendarService.findCalendarByDate(date1);
        return  ResponseUtil.make_response(calendar, "success", 200);
    }

    @RequestMapping(value = "/favourite/{userid}/{songid}", method = {RequestMethod.POST})
    public Object favourite(@PathVariable("userid") Integer userid, @PathVariable("songid") Integer songid){
        Favourite favourite = new Favourite();
        favourite.setSong_id(songid);
        favourite.setUser_id(userid);
        homeService.favouriteCreate(favourite);
        return ResponseUtil.make_response(null, "success", 200);
    }

    @RequestMapping(value = "/favourite/{userid}/{songid}", method = {RequestMethod.DELETE})
    public Object delfavourite(@PathVariable("userid") Integer userid, @PathVariable("songid") Integer songid){
        homeService.delFavourite(userid, songid);
        return ResponseUtil.make_response(null, "success", 200);
    }

    @RequestMapping(value = "/favourite/list")
        public Object favouritelist(@RequestParam(value = "user_id") Integer user_id, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageHelper.startPage(page, size);
        List<FavouriteListSerializer> favourites = homeService.getFavouriteListByUserId(user_id);
        PageInfo<FavouriteListSerializer> pageInfo = new PageInfo<>(favourites);
        return pageInfo;
    }

    @RequestMapping(value = "/setting", method = {RequestMethod.GET})
    public Object getSetting(){
        Setting setting = homeService.getSetting();
        return ResponseUtil.make_response(setting, "success", 200);
    }

        @RequestMapping(value = "/getsong", method = {RequestMethod.GET})
    public Object getSongUrl(@RequestParam(value = "user_id") Integer user_id, @RequestParam(value = "id") Integer id){
        User user = userService.findUserById(user_id);
        if (user != null && user.getIsvip() != 0){
            Song song = homeService.getSongById(id);
            PlayRecord playRecord = new PlayRecord();
            playRecord.setUser_id(user_id);
            playRecord.setSong_id(song.getId());
            homeService.addPlayRecord(playRecord);
            return ResponseUtil.make_response(song, "success", 200);
        }
        return ResponseUtil.make_response(null, "你不是会员！", 100);
    }
}
