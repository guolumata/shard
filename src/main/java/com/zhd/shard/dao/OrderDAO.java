package com.zhd.shard.dao;

import java.util.List;

import com.zhd.shard.dto.OrderPO;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-29
 */
public interface OrderDAO {
    int insert(OrderPO po);

    int update(OrderPO po);

    OrderPO getById(String id);

    List<OrderPO> getByUserId(String userId);

    OrderPO getByIdAndUserId(String id, String userId);
}
