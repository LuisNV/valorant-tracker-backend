package dev.luisnv.tracker.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "henrik")
public record HenrikProperties(String baseUrl, String apiKey) {
}
