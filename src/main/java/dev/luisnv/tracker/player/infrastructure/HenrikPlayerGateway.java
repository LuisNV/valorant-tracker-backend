package dev.luisnv.tracker.player.infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import dev.luisnv.tracker.player.domain.Player;
import dev.luisnv.tracker.shared.infrastructure.henrik.HenrikClient;
import dev.luisnv.tracker.player.domain.PlayerGateway;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class HenrikPlayerGateway implements PlayerGateway {

    private final HenrikClient henrikClient;

    public HenrikPlayerGateway(HenrikClient henrikClient) {
        this.henrikClient = henrikClient;
    }

    @Override
    @Cacheable(cacheNames = "players", key = "#name.toLowerCase() + '#' + #tag.toLowerCase()")
    public Player findByRiotId(String name, String tag) {
        JsonNode data = henrikClient.getAccount(name, tag);
        return new Player(
                data.path("puuid").asText(),
                data.path("name").asText(name),
                data.path("tag").asText(tag),
                data.path("region").asText(),
                data.path("account_level").asInt()
        );
    }
}
