package dev.luisnv.tracker.player;

public record PlayerProfileResponse(
        String puuid,
        String name,
        String tag,
        String region,
        int accountLevel
) {
}
