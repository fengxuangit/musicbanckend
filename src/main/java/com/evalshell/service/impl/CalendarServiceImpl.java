package com.evalshell.service.impl;

import com.alibaba.fastjson.JSON;
import com.evalshell.bean.model.Calendar;
import com.evalshell.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    CalendarService calendarService;


    @Override
    public Calendar findCalendarByDate(Date date) {
        Calendar calendar = calendarService.findCalendarByDate(date);
        calendar.setWordObject(JSON.parseObject(calendar.getWord()));
        return calendar;
    }
}
