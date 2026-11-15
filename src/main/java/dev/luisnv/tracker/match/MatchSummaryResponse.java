package dev.luisnv.tracker.match;

import java.time.Instant;

public record MatchSummaryResponse(
        String matchId,
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

    static MatchSummaryResponse from(MatchEntity m) {
        return new MatchSummaryResponse(
                m.getMatchId(), m.getMap(), m.getMode(), m.getAgent(),
                m.getKills(), m.getDeaths(), m.getAssists(), m.getScore(),
                m.getRoundsWon(), m.getRoundsLost(), m.isWon(), m.getStartedAt());
    }
}
