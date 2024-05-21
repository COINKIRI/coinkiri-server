package coinkiri.core.domain.follow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.follow.Follow;
import coinkiri.core.domain.member.Member;

public interface FollowRepository extends JpaRepository<Follow, Long> {

	void deleteByFollowerAndFollowing(Member follower, Member following);
}
