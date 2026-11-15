package dev.luisnv.tracker.domain.model;

public record Player(
        String puuid,
        String name,
        String tag,
        String region,
        int accountLevel
) {
}
