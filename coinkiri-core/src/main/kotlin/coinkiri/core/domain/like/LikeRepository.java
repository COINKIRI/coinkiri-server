package coinkiri.core.domain.like;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.member.Member;
import coinkiri.core.domain.post.Post;

public interface LikeRepository extends JpaRepository<Like, Long> {

	boolean existsByMemberAndPost(Member member, Post post);

	void deleteByMemberAndPost(Member member, Post post);

	List<Like> findByMember(Member member);
}
