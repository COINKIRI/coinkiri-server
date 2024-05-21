package coinkiri.core.domain.interest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.interest.Interest;

public interface InterestRepository extends JpaRepository<Interest, Long> {
}
