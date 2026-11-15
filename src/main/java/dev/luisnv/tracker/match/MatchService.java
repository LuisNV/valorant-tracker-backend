package dev.luisnv.tracker.match;

import dev.luisnv.tracker.player.PlayerService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final PlayerService playerService;

    public MatchService(MatchRepository matchRepository, PlayerService playerService) {
        this.matchRepository = matchRepository;
        this.playerService = playerService;
    }

    /** Matches already stored for this player, newest first. */
    @Transactional(readOnly = true)
    public List<MatchSummaryResponse> getRecentMatches(String name, String tag, int limit) {
        String puuid = playerService.getProfile(name, tag).puuid();
        return matchRepository.findByPuuidOrderByStartedAtDesc(puuid, PageRequest.of(0, limit))
                .stream()
                .map(MatchSummaryResponse::from)
                .toList();
    }

    // TODO: syncMatches(name, tag) — fetch the match list from HenrikClient, skip ones where
    // existsByMatchIdAndPuuid is true, map the rest to MatchEntity and save them.
}
