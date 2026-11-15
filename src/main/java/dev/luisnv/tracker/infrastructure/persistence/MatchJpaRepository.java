package dev.luisnv.tracker.infrastructure.persistence;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface MatchJpaRepository extends JpaRepository<MatchJpaEntity, Long> {

    List<MatchJpaEntity> findByPuuidOrderByStartedAtDesc(String puuid, Pageable pageable);

    boolean existsByMatchIdAndPuuid(String matchId, String puuid);
}
