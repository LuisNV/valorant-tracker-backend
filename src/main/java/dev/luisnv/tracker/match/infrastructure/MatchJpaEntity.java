package dev.luisnv.tracker.match.infrastructure;

import dev.luisnv.tracker.match.domain.Match;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "matches")
public class MatchJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "match_id", nullable = false)
    private String matchId;

    @Column(nullable = false)
    private String puuid;

    @Column(nullable = false)
    private String map;

    @Column(nullable = false)
    private String mode;

    @Column(nullable = false)
    private String agent;

    @Column(nullable = false)
    private int kills;

    @Column(nullable = false)
    private int deaths;

    @Column(nullable = false)
    private int assists;

    @Column(nullable = false)
    private int score;

    @Column(name = "rounds_won", nullable = false)
    private int roundsWon;

    @Column(name = "rounds_lost", nullable = false)
    private int roundsLost;

    @Column(nullable = false)
    private boolean won;

    @Column(name = "started_at", nullable = false)
    private Instant startedAt;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private Instant createdAt;

    protected MatchJpaEntity() {
    }

    static MatchJpaEntity fromDomain(Match match) {
        MatchJpaEntity entity = new MatchJpaEntity();
        entity.matchId = match.matchId();
        entity.puuid = match.puuid();
        entity.map = match.map();
        entity.mode = match.mode();
        entity.agent = match.agent();
        entity.kills = match.kills();
        entity.deaths = match.deaths();
        entity.assists = match.assists();
        entity.score = match.score();
        entity.roundsWon = match.roundsWon();
        entity.roundsLost = match.roundsLost();
        entity.won = match.won();
        entity.startedAt = match.startedAt();
        return entity;
    }

    Match toDomain() {
        return new Match(matchId, puuid, map, mode, agent, kills, deaths, assists, score,
                roundsWon, roundsLost, won, startedAt);
    }
}
