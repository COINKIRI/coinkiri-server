package coinkiri.core.domain.post.analysis.repository;

import java.util.List;

import coinkiri.core.domain.post.analysis.Analysis;

public interface AnalysisRepositoryDsl {

	List<Analysis> findAllWithMemberAndCoinAndCommentAndLike();
}
