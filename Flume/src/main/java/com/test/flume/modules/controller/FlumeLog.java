package com.test.flume.modules.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 类 描 述：flume消息
 * 创建时间：2023/11/11 16:22
 *
 * @author：liupeihan
 */
public class FlumeLog {

    private static Logger log = LogManager.getLogger("flumeLog");

    /**
     * 输出日志
     */
    public static void info(String message) {
        if (log.isInfoEnabled()) {
            log.info(message);
        }
    }
}