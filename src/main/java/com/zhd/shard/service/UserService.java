package com.zhd.shard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhd.shard.dao.UserDAO;
import com.zhd.shard.dto.UserPO;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-28
 */
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

    public UserPO getById(String id) {
        UserPO po = userDAO.getById(id);
        return po;
    }
}
