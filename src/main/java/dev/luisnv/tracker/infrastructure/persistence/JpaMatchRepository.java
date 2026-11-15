package dev.luisnv.tracker.infrastructure.persistence;

import dev.luisnv.tracker.domain.model.Match;
import dev.luisnv.tracker.domain.repository.MatchRepository;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/** Adapter: implements the domain MatchRepository port on top of Spring Data JPA. */
@Repository
public class JpaMatchRepository implements MatchRepository {

    private final MatchJpaRepository jpaRepository;

    public JpaMatchRepository(MatchJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Match> findRecentByPuuid(String puuid, int limit) {
        return jpaRepository.findByPuuidOrderByStartedAtDesc(puuid, PageRequest.of(0, limit))
                .stream()
                .map(MatchJpaEntity::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(String matchId, String puuid) {
        return jpaRepository.existsByMatchIdAndPuuid(matchId, puuid);
    }

    @Override
    @Transactional
    public void saveAll(List<Match> matches) {
        jpaRepository.saveAll(matches.stream().map(MatchJpaEntity::fromDomain).toList());
    }
}
