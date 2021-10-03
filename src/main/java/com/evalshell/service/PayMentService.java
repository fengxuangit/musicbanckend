package com.evalshell.service;

import com.evalshell.bean.model.OrderInfo;

public interface PayMentService {
    void createOrder(OrderInfo orderInfo);

    OrderInfo getOrderByPayNo(String pay_no);

    void updateOrder(OrderInfo orderInfo);
}
