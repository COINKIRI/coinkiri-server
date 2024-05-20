package coinkiri.core.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.member.Member;
import coinkiri.core.domain.member.SocialType;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findBySocialId(String socialId); // 소셜 id로 사용자 조회

	boolean existsBySocialId(String socialId); // 소셜 id로 사용자 존재 여부 조회

	Member findBySocialIdAndSocialType(String socialId, SocialType socialType); // 소셜 id와 소셜 타입으로 사용자 조회

	boolean existsByNickname(String nickname); // 닉네임으로 사용자 존재 여부 조회
}
