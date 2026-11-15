package dev.luisnv.tracker.shared.infrastructure.henrik;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "henrik")
public record HenrikProperties(String baseUrl, String apiKey) {
}
