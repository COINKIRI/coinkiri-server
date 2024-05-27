package coinkiri.core.domain.post.repository.post;

import coinkiri.core.domain.post.Post;

public interface PostRepositoryDsl {

	Post findPostById(Long postId);
}
