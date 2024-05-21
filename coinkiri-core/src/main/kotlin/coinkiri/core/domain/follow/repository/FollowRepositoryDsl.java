package coinkiri.core.domain.follow.repository;

import java.util.List;

import coinkiri.core.domain.follow.Follow;

public interface FollowRepositoryDsl {

	List<Follow> findWithFollowerAndFollowingByFollowerId(Long followerId);

	List<Follow> findWithFollowerAndFollowingByFollowingId(Long followingId);
}
