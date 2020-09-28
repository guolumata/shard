package com.zhd.shard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.zhd.shard.dao")
public class ShardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardApplication.class, args);
    }

}
