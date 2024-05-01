package coinkiri.core.domain.coin.repository

import coinkiri.core.domain.coin.Coin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @ClassName    : CoinRepository
 */
interface CoinRepository : JpaRepository<Coin, Long> {

}
