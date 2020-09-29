package com.zhd.shard.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-29
 */
public class SingleKeyTableShardingAlgorithmImpl implements SingleKeyTableShardingAlgorithm<String> {

    /**
     * Sharding with equal operator.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue sharding value
     * @return sharding results for data sources or tables's names
     */
    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
        int size = availableTargetNames.size();
        boolean random = size == 1;
        for (String tableName : availableTargetNames) {
            if (random) {
                return tableName;
            }
            if (tableName.endsWith(shardingValue.getValue().hashCode() % size + "")) {
                return tableName;
            }
        }
        return null;
    }

    /**
     * Sharding with in operator.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue sharding value
     * @return sharding results for data sources or tables's names
     */
    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames,
            ShardingValue<String> shardingValue) {
        int size = availableTargetNames.size();
        boolean random = size == 1;
        List<String> list = new ArrayList<>(size);
        for (String tableName : availableTargetNames) {
            if (random) {
                list.add(tableName);
                return list;
            }
            if (tableName.endsWith(shardingValue.getValue().hashCode() % size + "")) {
                list.add(tableName);
            }
        }
        return list;
    }

    /**
     * Sharding with between operator.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue sharding value
     * @return sharding results for data sources or tables's names
     */
    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
            ShardingValue<String> shardingValue) {
        return doInSharding(availableTargetNames, shardingValue);
    }
}
