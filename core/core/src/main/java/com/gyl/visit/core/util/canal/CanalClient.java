package com.gyl.visit.core.util.canal;

import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public class CanalClient implements InitializingBean {
    private List<String> cachedTables;
    private String hostname;
    private Integer port = 11111;

    private String destination = "example";
    private String username = "canal";
    private String password;
    private int batchSize = 100;

    @Override
    public void afterPropertiesSet() throws Exception {
//        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(hostname, port), destination, username, password);
//        new Thread(new CanalListener(connector,cachedTables,batchSize)).start();
    }

    public void setCachedTables(List<String> cachedTables) {
        this.cachedTables = cachedTables;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }
}
