package com.gyl.visit.core.util;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;

public class HostUtil {

    public static final String CONTEXT = "/nf";

    public static String getLocalHost() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        return inetAddress.toString();
    }

    public static String getIp() throws UnknownHostException {
        String localHost = getLocalHost();
        return localHost.substring(localHost.lastIndexOf('/') + 1);
    }

    public static String getPort() throws MalformedObjectNameException {
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
                Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
        String port = objectNames.iterator().next().getKeyProperty("port");
        return port;
    }

    public static String getlocalUrl() throws UnknownHostException, MalformedObjectNameException {
        return getIp() + ":" + getPort() + CONTEXT;
    }
}
