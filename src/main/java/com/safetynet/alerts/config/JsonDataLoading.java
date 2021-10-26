package com.safetynet.alerts.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class JsonDataLoading {

    private static final Logger log = LogManager.getLogger(JsonDataLoading.class);

    @Value("classpath:data.json")
    Resource resourceFile;


    @Bean
    public Data data() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(resourceFile.getInputStream(), Data.class);
        } catch (IOException e) {
            log.error(e);
        }

        return new Data();
    }

}
