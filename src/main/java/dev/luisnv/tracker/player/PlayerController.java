package dev.luisnv.tracker.player;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{name}/{tag}")
    public PlayerProfileResponse getProfile(@PathVariable String name, @PathVariable String tag) {
        return playerService.getProfile(name, tag);
    }
}
