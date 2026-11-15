package dev.luisnv.tracker.player.domain;


/** Port: looks a player up in the external stats provider. Implemented in infrastructure. */
public interface PlayerGateway {

    Player findByRiotId(String name, String tag);
}
