package com.zhd.shard.config;

import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-29
 */
public class SingleKeyDatabaseShardingAlgorithmImpl extends SingleKeyTableShardingAlgorithmImpl
        implements SingleKeyDatabaseShardingAlgorithm<String> {
}
