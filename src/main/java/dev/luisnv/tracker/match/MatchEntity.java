package dev.luisnv.tracker.match;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

/** One player's result in one match. */
@Entity
@Table(name = "matches")
public class MatchEntity {

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

    protected MatchEntity() {
    }

    public Long getId() { return id; }
    public String getMatchId() { return matchId; }
    public void setMatchId(String matchId) { this.matchId = matchId; }
    public String getPuuid() { return puuid; }
    public void setPuuid(String puuid) { this.puuid = puuid; }
    public String getMap() { return map; }
    public void setMap(String map) { this.map = map; }
    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }
    public String getAgent() { return agent; }
    public void setAgent(String agent) { this.agent = agent; }
    public int getKills() { return kills; }
    public void setKills(int kills) { this.kills = kills; }
    public int getDeaths() { return deaths; }
    public void setDeaths(int deaths) { this.deaths = deaths; }
    public int getAssists() { return assists; }
    public void setAssists(int assists) { this.assists = assists; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public int getRoundsWon() { return roundsWon; }
    public void setRoundsWon(int roundsWon) { this.roundsWon = roundsWon; }
    public int getRoundsLost() { return roundsLost; }
    public void setRoundsLost(int roundsLost) { this.roundsLost = roundsLost; }
    public boolean isWon() { return won; }
    public void setWon(boolean won) { this.won = won; }
    public Instant getStartedAt() { return startedAt; }
    public void setStartedAt(Instant startedAt) { this.startedAt = startedAt; }
    public Instant getCreatedAt() { return createdAt; }
}
