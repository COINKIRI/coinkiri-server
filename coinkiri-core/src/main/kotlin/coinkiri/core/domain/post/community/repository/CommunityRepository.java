package coinkiri.core.domain.post.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.post.community.Community;


public interface CommunityRepository extends JpaRepository<Community, Long>, CommunityRepositoryDsl{
}
