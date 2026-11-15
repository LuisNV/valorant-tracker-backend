package dev.luisnv.tracker.domain.model;

import java.time.Instant;

/** One player's result in one match. */
public record Match(
        String matchId,
        String puuid,
        String map,
        String mode,
        String agent,
        int kills,
        int deaths,
        int assists,
        int score,
        int roundsWon,
        int roundsLost,
        boolean won,
        Instant startedAt
) {
}
