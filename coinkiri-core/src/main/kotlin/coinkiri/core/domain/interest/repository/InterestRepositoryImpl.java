package coinkiri.core.domain.interest.repository;

import static coinkiri.core.domain.interest.QInterest.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import coinkiri.core.domain.interest.Interest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InterestRepositoryImpl implements InterestRepositoryDsl {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Interest> findWithMemberAndCoinByMemberId(Long memberId) {
		return queryFactory
			.selectFrom(interest)
			.innerJoin(interest.member).fetchJoin()
			.innerJoin(interest.coin).fetchJoin()
			.where(interest.member.id.eq(memberId))
			.fetch();
	}

}
