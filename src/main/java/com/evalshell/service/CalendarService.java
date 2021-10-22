package com.evalshell.service;

import com.evalshell.bean.model.Calendar;

import java.util.Date;

public interface CalendarService {
    Calendar findCalendarByDate(Date date);

    Calendar findCalendarByDateSingle(Date date);

    void update(Calendar calendar);
}
