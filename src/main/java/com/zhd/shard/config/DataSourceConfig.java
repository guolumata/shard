package com.zhd.shard.config;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-29
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "dataSource0")
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    public DataSource dataSource0() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.test2")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSourceRule dataSourceRule(@Qualifier("dataSource0") DataSource dataSource0,
            @Qualifier("dataSource1") DataSource dataSource1) {
        Map<String, DataSource> map = new HashMap<>();
        map.put("dataSource0", dataSource0);
        map.put("dataSource1", dataSource1);
        DataSourceRule rule = new DataSourceRule(map, "dataSource0");
        return rule;
    }

    @Bean
    public ShardingRule shardingRule(DataSourceRule dataSourceRule) {
        TableRule tableRule = TableRule.builder("t_order")
                .actualTables(Arrays.asList("t_order_0", "t_order_1", "t_order_2"))
                .tableShardingStrategy(new TableShardingStrategy("id", new SingleKeyTableShardingAlgorithmImpl()))
                .dataSourceRule(dataSourceRule)
                .build();

        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(tableRule))
                .databaseShardingStrategy(
                        new DatabaseShardingStrategy("user_id", new SingleKeyDatabaseShardingAlgorithmImpl()))
                .build();
        return shardingRule;
    }

    @Bean("dataSource")
    public DataSource shardingDataSource(ShardingRule shardingRule) throws SQLException {
        Properties props = new Properties();
        props.put("sql.show", "true");
        return ShardingDataSourceFactory.createDataSource(shardingRule, props);
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mybatis/mapper/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
            SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
