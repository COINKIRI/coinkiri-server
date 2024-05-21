package coinkiri.core.domain.interest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.interest.Interest;

public interface InterestRepository extends JpaRepository<Interest, Long> {

	List<Interest> findByMemberId(Long memberId);
}
