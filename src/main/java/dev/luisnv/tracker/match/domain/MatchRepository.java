package dev.luisnv.tracker.match.domain;

import java.util.List;

/** Port: stored matches. Implemented in infrastructure. */
public interface MatchRepository {

    List<Match> findRecentByPuuid(String puuid, int limit);

    boolean exists(String matchId, String puuid);

    void saveAll(List<Match> matches);
}
