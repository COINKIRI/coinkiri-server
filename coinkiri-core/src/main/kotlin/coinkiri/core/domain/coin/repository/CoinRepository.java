package coinkiri.core.domain.coin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.coin.Coin;

/**
 * @ClassName    : CoinRepository
 *
 */
public interface CoinRepository extends JpaRepository<Coin, Long> { // 현재 public 생략하면 안됨.. 왜지?
}
