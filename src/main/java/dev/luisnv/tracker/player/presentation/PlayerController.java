package dev.luisnv.tracker.player.presentation;

import dev.luisnv.tracker.player.application.GetPlayerProfileUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/players/{name}/{tag}")
public class PlayerController {

    private final GetPlayerProfileUseCase getPlayerProfile;

    public PlayerController(GetPlayerProfileUseCase getPlayerProfile) {
        this.getPlayerProfile = getPlayerProfile;
    }

    @GetMapping
    public PlayerProfileResponse getProfile(@PathVariable String name, @PathVariable String tag) {
        return PlayerProfileResponse.from(getPlayerProfile.execute(name, tag));
    }
}
