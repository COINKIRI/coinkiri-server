package coinkiri.core.domain.coin.repository;

import static coinkiri.core.domain.coin.QCoin.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import coinkiri.core.domain.coin.Coin;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CoinRepositoryImpl {
	private final JPAQueryFactory queryFactory;

	public List<Coin> findAllCoin() {
		return queryFactory.selectFrom(coin)
			.fetch();
	}
}
