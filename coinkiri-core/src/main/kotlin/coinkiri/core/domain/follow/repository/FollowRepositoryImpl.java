package coinkiri.core.domain.follow.repository;

import static coinkiri.core.domain.follow.QFollow.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import coinkiri.core.domain.follow.Follow;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepositoryDsl {
	private final JPAQueryFactory queryFactory;

	public List<Follow> findWithFollowerAndFollowingByFollowerId(Long followerId) {
		return queryFactory
			.selectFrom(follow)
			.innerJoin(follow.follower).fetchJoin()
			.innerJoin(follow.following).fetchJoin()
			.where(follow.follower.id.eq(followerId))
			.fetch();
	}

	public List<Follow> findWithFollowerAndFollowingByFollowingId(Long followingId) {
		return queryFactory
			.selectFrom(follow)
			.innerJoin(follow.follower).fetchJoin()
			.innerJoin(follow.following).fetchJoin()
			.where(follow.following.id.eq(followingId))
			.fetch();
	}
}
