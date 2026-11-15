package dev.luisnv.tracker.player;

import com.fasterxml.jackson.databind.JsonNode;
import dev.luisnv.tracker.client.HenrikClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final HenrikClient henrikClient;

    public PlayerService(HenrikClient henrikClient) {
        this.henrikClient = henrikClient;
    }

    @Cacheable(cacheNames = "players", key = "#name.toLowerCase() + '#' + #tag.toLowerCase()")
    public PlayerProfileResponse getProfile(String name, String tag) {
        JsonNode data = henrikClient.getAccount(name, tag);
        return new PlayerProfileResponse(
                data.path("puuid").asText(),
                data.path("name").asText(name),
                data.path("tag").asText(tag),
                data.path("region").asText(),
                data.path("account_level").asInt()
        );
    }
}
