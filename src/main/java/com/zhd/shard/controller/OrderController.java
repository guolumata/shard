package com.zhd.shard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhd.shard.dto.OrderPO;
import com.zhd.shard.service.OrderService;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-29
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("save")
    public int insert(OrderPO po) {
        return orderService.insert(po);
    }

    @PutMapping("update")
    public int update(OrderPO po) {
        return orderService.update(po);
    }

    @GetMapping("getById")
    public OrderPO getById(String id) {
        return orderService.getById(id);
    }

    @GetMapping("getByUserId")
    public List<OrderPO> getByUserId(String userId) {
        return orderService.getByUserId(userId);
    }

    @GetMapping("getByIdAndUserId")
    public OrderPO getByIdAndUserId(String id, String userId) {
        return orderService.getByIdAndUserId(id, userId);
    }
}
