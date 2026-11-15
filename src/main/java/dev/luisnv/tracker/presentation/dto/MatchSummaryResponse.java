package dev.luisnv.tracker.presentation.dto;

import dev.luisnv.tracker.domain.model.Match;
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

    public static MatchSummaryResponse from(Match m) {
        return new MatchSummaryResponse(
                m.matchId(), m.map(), m.mode(), m.agent(),
                m.kills(), m.deaths(), m.assists(), m.score(),
                m.roundsWon(), m.roundsLost(), m.won(), m.startedAt());
    }
}
