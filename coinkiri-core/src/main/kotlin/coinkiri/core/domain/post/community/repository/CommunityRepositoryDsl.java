package coinkiri.core.domain.post.community.repository;

import java.util.List;

import coinkiri.core.domain.post.community.Community;

public interface CommunityRepositoryDsl {

	List<Community> findAllWithMember();

	List<Community> findAllWithMemberAndCommentAndLike();

	Community findOneWithMemberAndCommentAndLike(Long id);
}
