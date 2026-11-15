package dev.luisnv.tracker.match;

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

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public List<MatchSummaryResponse> getRecentMatches(
            @PathVariable String name,
            @PathVariable String tag,
            @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit) {
        return matchService.getRecentMatches(name, tag, limit);
    }
}
