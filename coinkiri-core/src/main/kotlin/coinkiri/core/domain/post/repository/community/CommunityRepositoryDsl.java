package coinkiri.core.domain.post.repository.community;

import java.util.List;

import coinkiri.core.domain.post.Community;

public interface CommunityRepositoryDsl {

	List<Community> findAllWithMember();

	List<Community> findAllWithMemberAndImageAndComment();
}
