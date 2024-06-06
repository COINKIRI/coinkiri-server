package coinkiri.core.domain.post.analysis.repository;

import java.util.List;

import coinkiri.core.domain.post.analysis.Analysis;

public interface AnalysisRepositoryDsl {

	Analysis findOneWithMemberAndCoinAndCommentAndLike(Long id);
	List<Analysis> findAllWithMemberAndCoinAndCommentAndLike();

	List<Analysis> findAnalysisByCoinList(List<Long> coinList);
}
