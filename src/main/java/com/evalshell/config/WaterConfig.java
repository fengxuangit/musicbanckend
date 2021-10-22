package com.evalshell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource(value = "classpath:application.yml")
@ConfigurationProperties(prefix = "water")
public class WaterConfig {
    private String path;
}
