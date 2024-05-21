package coinkiri.core.domain.follow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.follow.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
