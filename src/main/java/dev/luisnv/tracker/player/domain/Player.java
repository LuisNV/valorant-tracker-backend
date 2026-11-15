package dev.luisnv.tracker.player.domain;

public record Player(
        String puuid,
        String name,
        String tag,
        String region,
        int accountLevel
) {
}
