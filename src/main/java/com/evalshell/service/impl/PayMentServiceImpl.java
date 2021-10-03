package com.evalshell.service.impl;

import com.evalshell.bean.model.OrderInfo;
import com.evalshell.service.PayMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class PayMentServiceImpl implements PayMentService {
    @Autowired
    PayMentService payMentService;

    @Override
    public void createOrder(OrderInfo orderInfo) {
        orderInfo.setStatus(0);
        Date date = new Date();
        orderInfo.setInsert_tm(date);
        orderInfo.setUpdate_tm(date);
        payMentService.createOrder(orderInfo);
    }

    @Override
    public OrderInfo getOrderByPayNo(String pay_no) {
        return payMentService.getOrderByPayNo(pay_no);
    }

    @Override
    public void updateOrder(OrderInfo orderInfo) {
        if (orderInfo.getStatus() == 1){
            Date date = new Date();
            orderInfo.setPay_tm(date);
        }
        payMentService.updateOrder(orderInfo);
    }
}
