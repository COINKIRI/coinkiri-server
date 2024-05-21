package coinkiri.api.service.coin

import coinkiri.api.controller.coin.dto.response.CoinResponseDto
import coinkiri.core.domain.coin.Coin
import coinkiri.core.domain.coin.repository.CoinRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @ClassName    : CoinUpdateService
 *
 */
@Service
class CoinService (
    private val coinRepository: CoinRepository
){
    @Transactional(readOnly = true)
    fun findCoinList(): List<CoinResponseDto> {
        return coinRepository.findAll().map {
            CoinResponseDto(
                it.coinId,
                it.market,
                it.koreanName,
                it.englishName,
                it.symbolImage
            )
        }
    }
}