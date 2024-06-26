package coinkiri.core.domain.follow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.follow.Follow;
import coinkiri.core.domain.member.Member;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryDsl {

	void deleteByFollowerAndFollowing(Member follower, Member following);

	Integer countByFollowerId(Long followerId);

	Integer countByFollowingId(Long followingId);

	boolean existsByFollowerAndFollowing(Member follower, Member following);
}
