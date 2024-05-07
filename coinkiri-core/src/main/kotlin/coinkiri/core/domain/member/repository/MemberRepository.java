package coinkiri.core.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.member.Member;

/**
 * @ClassName    : $ 클래스에 대한 설명을 작성합니다.
 *
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findBySocialId(String socialId); // 소셜 id로 사용자 조회
}
