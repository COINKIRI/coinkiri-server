package coinkiri.core.domain.comment.repository;

import static coinkiri.core.domain.comment.QComment.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import coinkiri.core.domain.comment.Comment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryDsl {
	private final JPAQueryFactory queryFactory;

	// findWithMemberByPostId 메소드
	// post_id를 받아서 해당 post_id에 해당하는 member를 fetchjoin으로 가져오는 메소드
	@Override
	public List<Comment> findWithMemberByPostId(Long postId) {
		return queryFactory.selectFrom(comment)
			.innerJoin(comment.member)
			.fetchJoin()
			.where(comment.post.id.eq(postId))
			.fetch();
	}
}
