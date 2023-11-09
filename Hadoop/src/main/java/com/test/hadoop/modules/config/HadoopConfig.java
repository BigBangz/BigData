package com.test.hadoop.modules.config;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HadoopConfig {
    @Bean
    public FileSystem initFileSystem() throws Exception {
        //创建一个配置对象
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
        //指定HDFS的地址
        conf.set("fs.defaultFS","hdfs://192.168.117.117:9000");
        //获取操作HDFS的对象
        return FileSystem.get(conf);
    }
}
