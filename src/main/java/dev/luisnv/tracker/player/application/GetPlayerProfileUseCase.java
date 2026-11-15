package dev.luisnv.tracker.player.application;

import dev.luisnv.tracker.player.domain.Player;
import dev.luisnv.tracker.player.domain.PlayerGateway;
import org.springframework.stereotype.Service;

@Service
public class GetPlayerProfileUseCase {

    private final PlayerGateway playerGateway;

    public GetPlayerProfileUseCase(PlayerGateway playerGateway) {
        this.playerGateway = playerGateway;
    }

    public Player execute(String name, String tag) {
        return playerGateway.findByRiotId(name, tag);
    }
}
