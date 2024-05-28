package coinkiri.core.domain.post.analysis.repository;

import static coinkiri.core.domain.post.analysis.QAnalysis.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import coinkiri.core.domain.post.analysis.Analysis;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AnalysisRepositoryImpl implements AnalysisRepositoryDsl{

	private final JPAQueryFactory queryFactory;

	// 전체조회 findAllWithMemberAndCommentAndLike
	public List<Analysis> findAllWithMemberAndCoinAndCommentAndLike() {
		return queryFactory.selectFrom(analysis)
			.leftJoin(analysis.member).fetchJoin()
			.leftJoin(analysis.coin).fetchJoin()
			.leftJoin(analysis.comments).fetchJoin()
			// .leftJoin(analysis.likes).fetchJoin() -> default_batch_fetch_size 설정으로 인해 fetchJoin() 사용하지 않음
			.orderBy(analysis.createdAt.desc()) // createdAt 기준으로 정렬
			.fetch();

	}
}
