package coinkiri.core.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
