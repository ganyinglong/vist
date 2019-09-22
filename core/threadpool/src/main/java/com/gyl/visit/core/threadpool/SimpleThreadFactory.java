package com.gyl.visit.core.threadpool;

import java.util.concurrent.ThreadFactory;

/**
 * @author ganyinglong
 **/
public class SimpleThreadFactory implements ThreadFactory {
    private long count = 0;
    private String nameParttern;

    public SimpleThreadFactory(String nameParttern) {
        this.nameParttern = nameParttern;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, String.format(nameParttern, count++));
    }


}
