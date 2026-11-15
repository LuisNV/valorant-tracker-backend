package dev.luisnv.tracker.infrastructure.henrik;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

@Configuration
public class HenrikClientConfig {

    @Bean
    RestClient henrikRestClient(RestClient.Builder builder, HenrikProperties properties) {
        return builder
                .baseUrl(properties.baseUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, properties.apiKey())
                .build();
    }
}
