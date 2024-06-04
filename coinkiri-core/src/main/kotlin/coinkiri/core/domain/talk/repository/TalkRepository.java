package coinkiri.core.domain.talk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.talk.Talk;

public interface TalkRepository extends JpaRepository<Talk, Long>, TalkRepositoryDsl {
}
