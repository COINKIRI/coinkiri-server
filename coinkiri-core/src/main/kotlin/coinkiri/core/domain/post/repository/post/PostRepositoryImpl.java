package coinkiri.core.domain.post.repository.post;

import static coinkiri.core.domain.post.QPost.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import coinkiri.core.domain.post.Post;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryDsl {

	private final JPAQueryFactory queryFactory;

	@Override
	public Post findPostById(Long postId) {
		return queryFactory.selectFrom(post)
			.where(post.id.eq(postId))
			.fetchOne();
	}
}
