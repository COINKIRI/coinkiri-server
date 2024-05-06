package coinkiri.core.domain.coin.repository;

import coinkiri.core.domain.coin.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName    : CoinRepository
 */
public interface CoinRepository extends JpaRepository<Coin, Long>, CoinRepositoryQDsl {


}
