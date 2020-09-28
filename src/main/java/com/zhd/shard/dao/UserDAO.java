package com.zhd.shard.dao;

import com.zhd.shard.dto.UserPO;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-28
 */
//@Mapper
public interface UserDAO {
    //    @Select("select * from t_user where id = #{id} limit 1")
    UserPO getById(String id);
}
