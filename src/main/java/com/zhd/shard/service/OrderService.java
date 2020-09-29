package com.zhd.shard.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhd.shard.dao.OrderDAO;
import com.zhd.shard.dto.OrderPO;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-29
 */
@Service
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderDAO orderDAO;

    public int insert(OrderPO po) {
        return orderDAO.insert(po);
    }

    public int update(OrderPO po) {
        return orderDAO.update(po);
    }

    public OrderPO getById(String id) {
        return orderDAO.getById(id);
    }

    public List<OrderPO> getByUserId(String userId) {
        return orderDAO.getByUserId(userId);
    }

    public OrderPO getByIdAndUserId(String id, String userId) {
        return orderDAO.getByIdAndUserId(id, userId);
    }
}
