package com.zhd.shard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhd.shard.dto.UserPO;
import com.zhd.shard.service.UserService;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-28
 */
@RestController
@RequestMapping("/")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping(value = "{id}")
    public UserPO show(@PathVariable String id) {
        return userService.getById(id);
    }
}
