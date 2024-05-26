package coinkiri.core.domain.post.repository.community;

import static coinkiri.core.domain.post.QCommunity.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import coinkiri.core.domain.post.Community;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommunityRepositoryImpl implements CommunityRepositoryDsl {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Community> findAllWithMember() {
		return queryFactory.selectFrom(community)
			.leftJoin(community.member)
			.fetchJoin()
			.fetch();
	}

	// post+community+image(커뮤니티 글+이미지) + member(작성자) + comment(댓글) 한번에 조회
	@Override
	public List<Community> findAllWithMemberAndImageAndComment() {
		return queryFactory.selectFrom(community)
			.leftJoin(community.member).fetchJoin()
			.leftJoin(community.images).fetchJoin()
			.fetch();
	}
}
