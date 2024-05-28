package coinkiri.core.domain.post.analysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.post.analysis.Analysis;


public interface AnalysisRepository extends JpaRepository<Analysis, Long>, AnalysisRepositoryDsl {
}
