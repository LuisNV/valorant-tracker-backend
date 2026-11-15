CREATE TABLE matches (
    id           BIGSERIAL PRIMARY KEY,
    match_id     VARCHAR(64)  NOT NULL,
    puuid        VARCHAR(128) NOT NULL,
    map          VARCHAR(64)  NOT NULL,
    mode         VARCHAR(64)  NOT NULL,
    agent        VARCHAR(64)  NOT NULL,
    kills        INTEGER      NOT NULL,
    deaths       INTEGER      NOT NULL,
    assists      INTEGER      NOT NULL,
    score        INTEGER      NOT NULL,
    rounds_won   INTEGER      NOT NULL,
    rounds_lost  INTEGER      NOT NULL,
    won          BOOLEAN      NOT NULL,
    started_at   TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    CONSTRAINT uq_matches_match_player UNIQUE (match_id, puuid)
);

CREATE INDEX idx_matches_puuid_started ON matches (puuid, started_at DESC);
