package coinkiri.core.domain.coin.repository;

import java.util.List;

import coinkiri.core.domain.coin.Coin;

public interface CoinRepositoryDsl {
	List<Coin> findAllCoin();
}
