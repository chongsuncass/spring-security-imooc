package com.imooc.config;

import com.imooc.ClientStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class ClientConfig {
    private Logger logger = LoggerFactory.getLogger(ClientConfig.class);

    @Bean
    @ConditionalOnMissingBean
    ClientStarter clientStarter() {
        return new ClientStarter();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        logger.info("ClientConfig init()");
        clientStarter().init();
    }
}
