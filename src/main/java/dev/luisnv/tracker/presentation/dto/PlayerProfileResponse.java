package dev.luisnv.tracker.presentation.dto;

import dev.luisnv.tracker.domain.model.Player;

public record PlayerProfileResponse(
        String puuid,
        String name,
        String tag,
        String region,
        int accountLevel
) {

    public static PlayerProfileResponse from(Player player) {
        return new PlayerProfileResponse(
                player.puuid(), player.name(), player.tag(), player.region(), player.accountLevel());
    }
}
