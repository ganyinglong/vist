package com.gyl.visit.core.threadpool;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工厂
 *
 * @author ganyinglong
 */
@Component
public class ThreadPoolFactory implements InitializingBean {
    private static ExecutorService pool;

    public static ExecutorService getPool() {
        return pool;
    }

    @Override
    public void afterPropertiesSet() {
        pool = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024), new SimpleThreadFactory("common-pool-%d"), new ThreadPoolExecutor.AbortPolicy());
    }
}
