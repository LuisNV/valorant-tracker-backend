package dev.luisnv.tracker.presentation;

import dev.luisnv.tracker.application.GetPlayerProfileUseCase;
import dev.luisnv.tracker.application.GetRecentMatchesUseCase;
import dev.luisnv.tracker.presentation.dto.MatchSummaryResponse;
import dev.luisnv.tracker.presentation.dto.PlayerProfileResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/players/{name}/{tag}")
public class PlayerController {

    private final GetPlayerProfileUseCase getPlayerProfile;
    private final GetRecentMatchesUseCase getRecentMatches;

    public PlayerController(GetPlayerProfileUseCase getPlayerProfile, GetRecentMatchesUseCase getRecentMatches) {
        this.getPlayerProfile = getPlayerProfile;
        this.getRecentMatches = getRecentMatches;
    }

    @GetMapping
    public PlayerProfileResponse getProfile(@PathVariable String name, @PathVariable String tag) {
        return PlayerProfileResponse.from(getPlayerProfile.execute(name, tag));
    }

    @GetMapping("/matches")
    public List<MatchSummaryResponse> getRecentMatches(
            @PathVariable String name,
            @PathVariable String tag,
            @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit) {
        return getRecentMatches.execute(name, tag, limit).stream()
                .map(MatchSummaryResponse::from)
                .toList();
    }
}
