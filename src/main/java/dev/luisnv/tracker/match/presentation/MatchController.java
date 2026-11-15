package dev.luisnv.tracker.match.presentation;

import dev.luisnv.tracker.match.application.GetRecentMatchesUseCase;
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
@RequestMapping("/api/v1/players/{name}/{tag}/matches")
public class MatchController {

    private final GetRecentMatchesUseCase getRecentMatches;

    public MatchController(GetRecentMatchesUseCase getRecentMatches) {
        this.getRecentMatches = getRecentMatches;
    }

    @GetMapping
    public List<MatchSummaryResponse> getRecentMatches(
            @PathVariable String name,
            @PathVariable String tag,
            @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit) {
        return getRecentMatches.execute(name, tag, limit).stream()
                .map(MatchSummaryResponse::from)
                .toList();
    }
}
