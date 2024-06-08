package coinkiri.core.domain.post.analysis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.member.Member;
import coinkiri.core.domain.post.analysis.Analysis;


public interface AnalysisRepository extends JpaRepository<Analysis, Long>, AnalysisRepositoryDsl {

	List<Analysis> findByMember(Member member);
}
