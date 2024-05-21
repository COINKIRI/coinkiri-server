package coinkiri.core.domain.interest.repository;

import java.util.List;

import coinkiri.core.domain.interest.Interest;

public interface InterestRepositoryDsl {

	List<Interest> findWithMemberAndCoinByMemberId(Long memberId);
}
