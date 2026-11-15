package dev.luisnv.tracker.match.application;

import dev.luisnv.tracker.match.domain.Match;
import dev.luisnv.tracker.match.domain.MatchRepository;
import dev.luisnv.tracker.player.domain.PlayerGateway;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetRecentMatchesUseCase {

    private final PlayerGateway playerGateway;
    private final MatchRepository matchRepository;

    public GetRecentMatchesUseCase(PlayerGateway playerGateway, MatchRepository matchRepository) {
        this.playerGateway = playerGateway;
        this.matchRepository = matchRepository;
    }

    /** Matches already stored for this player, newest first. */
    public List<Match> execute(String name, String tag, int limit) {
        String puuid = playerGateway.findByRiotId(name, tag).puuid();
        return matchRepository.findRecentByPuuid(puuid, limit);
    }

    // TODO: add SyncMatchesUseCase in this package — fetch the match list from a MatchGateway port, skip ones
    // where matchRepository.exists(...) is true, and saveAll the rest.
}
