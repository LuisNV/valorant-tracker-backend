package dev.luisnv.tracker.domain.repository;

import dev.luisnv.tracker.domain.model.Player;

/** Port: looks a player up in the external stats provider. Implemented in infrastructure. */
public interface PlayerGateway {

    Player findByRiotId(String name, String tag);
}
