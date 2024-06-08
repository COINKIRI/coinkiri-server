package coinkiri.core.domain.post.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.member.Member;
import coinkiri.core.domain.post.community.Community;


public interface CommunityRepository extends JpaRepository<Community, Long>, CommunityRepositoryDsl{

	List<Community> findByMember(Member member);
}
