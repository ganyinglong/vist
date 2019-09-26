package com.gyl.visit.user.bootstrap;

import com.gyl.visit.user.common.UserConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication(scanBasePackages = "com.gyl.visit")
@MapperScan("com.gyl.visit.**.mapper")
@EnableConfigurationProperties(UserConfig.class)
public class AppBootstrapApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AppBootstrapApplication.class);
        springApplication.addListeners(new StartListener());
        springApplication.run(args);
    }

    public static class StartListener implements GenericApplicationListener {
        @Override
        public boolean supportsSourceType(Class<?> sourceType) {
            return true;
        }

        @Override
        public int getOrder() {
            return Ordered.HIGHEST_PRECEDENCE + 10;
        }

        @Override
        public boolean supportsEventType(ResolvableType resolvableType) {
            return true;
        }

        @Override
        public void onApplicationEvent(ApplicationEvent applicationEvent) {
            if (applicationEvent instanceof ApplicationEnvironmentPreparedEvent) {
                ConfigurableEnvironment environment = ((ApplicationEnvironmentPreparedEvent) applicationEvent).getEnvironment();
                MDC.put("spring.application.name", environment.getProperty("spring.application.name"));
                MDC.put("logging.path", environment.getProperty("logging.path"));
            }
        }
    }
}

