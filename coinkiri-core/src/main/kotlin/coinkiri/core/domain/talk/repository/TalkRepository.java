package coinkiri.core.domain.talk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.coin.Coin;
import coinkiri.core.domain.talk.Talk;

public interface TalkRepository extends JpaRepository<Talk, Long>, TalkRepositoryDsl {

	List<Talk> findByCoin(Coin coin);
}
