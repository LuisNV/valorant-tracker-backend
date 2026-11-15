package dev.luisnv.tracker.application;

import dev.luisnv.tracker.domain.model.Player;
import dev.luisnv.tracker.domain.repository.PlayerGateway;
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
