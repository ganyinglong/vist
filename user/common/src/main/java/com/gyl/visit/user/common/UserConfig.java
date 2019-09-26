package com.gyl.visit.user.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ganyinglong
 **/
@ConfigurationProperties(prefix = "visit.user")
public class UserConfig {
    private String ppp = "";
}
