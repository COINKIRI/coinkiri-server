package coinkiri.core.domain.comment.repository;

import java.util.List;

import coinkiri.core.domain.comment.Comment;

public interface CommentRepositoryDsl {

	List<Comment> findWithMemberByPostId(Long postId);
}
