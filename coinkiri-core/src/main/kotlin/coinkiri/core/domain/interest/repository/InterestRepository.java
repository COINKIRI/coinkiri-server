package coinkiri.core.domain.interest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.coin.Coin;
import coinkiri.core.domain.interest.Interest;
import coinkiri.core.domain.member.Member;

public interface InterestRepository extends JpaRepository<Interest, Long>, InterestRepositoryDsl {

	List<Interest> findByMemberId(Long memberId);

	boolean existsByMemberAndCoin(Member member, Coin coin);

	void deleteByMemberAndCoin(Member member, Coin coin);
}
