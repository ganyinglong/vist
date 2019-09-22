package com.gyl.visit.core.jedisclient;

import com.gyl.visit.core.util.DateUtil;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JedisClient {
    private static JedisPool pool;

    /**
     * 获取Jedis实例
     *
     * @return
     */
    public static Jedis getJedis() {
        return pool.getResource();
    }

    /**
     * 释放jedis连接资源
     *
     * @param jedis
     */
    public static void releaseJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
            if (jedis.isConnected()) {
                jedis.disconnect();
            }
        }
    }

    public static String get(String key) {
        Jedis jedis = getJedis();
        try {
            String s = jedis.get(key);
            return s;
        } finally {
            releaseJedis(jedis);
        }
    }

    /**
     * 设置今天内有效
     */
    public static void expireAtToday(String key) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + 1);
        c.set(Calendar.SECOND, 0);
        long timeInMillis = c.getTimeInMillis();
        Jedis jedis = getJedis();
        try {
            jedis.expireAt(key, DateUtil.toUnixTimeStamp(timeInMillis));
        } finally {
            releaseJedis(jedis);
        }
    }

    public static void expireAtDays(String key, int days) {
        Date date = DateUtil.afterDays(new Date(), days);
        long toUnixTimeStamp = DateUtil.toUnixTimeStamp(date.getTime());
        Jedis jedis = getJedis();
        try {
            jedis.expireAt(key, toUnixTimeStamp);
        } finally {
            releaseJedis(jedis);
        }
    }

    public void setPool(JedisPool pool) {
        JedisClient.pool = pool;
    }

    public static String stringSet(String key, String value) {
        Jedis resource = pool.getResource();
        try {
            return resource.set(key, value);
        } finally {
            releaseJedis(resource);
        }
    }

    /**
     * 根据key删除
     *
     * @param key
     * @return
     */
    public static long del(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.del(key);
        } finally {
            releaseJedis(jedis);
        }
    }

    public static Long geoadd(String key, double longitude, double latitude, String member) {
        Jedis jedis = getJedis();
        try {
            return jedis.geoadd(key, longitude, latitude, member);
        } finally {
            releaseJedis(jedis);
        }
    }

    public static List<GeoRadiusResponse> georadius(String key, double longitude, double latitude,
                                                    double radius, GeoUnit unit, GeoRadiusParam geoRadiusParam) {
        Jedis jedis = getJedis();
        try {
            return jedis.georadius(key, longitude, latitude, radius, unit, geoRadiusParam);
        } finally {
            releaseJedis(jedis);
        }
    }

    public static Long zrem(final String key, final String... members) {
        Jedis jedis = getJedis();
        try {
            return jedis.zrem(key, members);
        } finally {
            releaseJedis(jedis);
        }
    }

    public static Long zadd(String key, double score, String member) {
        Jedis jedis = getJedis();
        try {
            Long zadd = jedis.zadd(key, score, member);
            return zadd;
        } finally {
            releaseJedis(jedis);
        }
    }

    public static Double zscore(String key, String member) {
        Jedis jedis = getJedis();
        try {
            return jedis.zscore(key, member);
        } finally {
            releaseJedis(jedis);
        }
    }

    public static long zcard(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.zcard(key);
        } finally {
            releaseJedis(jedis);
        }
    }
}
