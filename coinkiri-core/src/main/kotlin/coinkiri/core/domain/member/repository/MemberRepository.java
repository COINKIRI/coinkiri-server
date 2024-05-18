package coinkiri.core.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.member.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findBySocialId(String socialId); // 소셜 id로 사용자 조회

	boolean existsBySocialId(String socialId); // 소셜 id로 사용자 존재 여부 조회
}
