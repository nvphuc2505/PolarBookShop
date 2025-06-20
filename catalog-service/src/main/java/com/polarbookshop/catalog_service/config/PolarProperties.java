package com.polarbookshop.catalog_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "polar")
@Getter
@Setter
public class PolarProperties {

    private String greeting;

}
