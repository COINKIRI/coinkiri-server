package coinkiri.core.domain.post.repository.community;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.post.Community;


public interface CommunityRepository extends JpaRepository<Community, Long>, CommunityRepositoryDsl{
}