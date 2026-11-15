package dev.luisnv.tracker.match;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

    List<MatchEntity> findByPuuidOrderByStartedAtDesc(String puuid, Pageable pageable);

    boolean existsByMatchIdAndPuuid(String matchId, String puuid);
}
