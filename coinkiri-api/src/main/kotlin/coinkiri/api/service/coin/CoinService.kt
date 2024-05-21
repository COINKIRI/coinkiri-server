package coinkiri.api.service.coin

import coinkiri.api.controller.UpbitApiCaller
import coinkiri.api.controller.coin.dto.response.CoinPriceDto
import coinkiri.api.controller.coin.dto.response.CoinResponseDto
import coinkiri.core.domain.coin.repository.CoinRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CoinService (
    private val coinRepository: CoinRepository,
    private val upbitApiCaller: UpbitApiCaller
){
    // 코인 리스트 조회
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

    // 코인 상세 조회 (200일 차트 조회)
    @Transactional(readOnly = true)
    fun findCoinDetail(coinId: Long) : CoinPriceDto {
        val coin = coinRepository.findById(coinId).orElseThrow { throw Exception("코인 정보가 없습니다.") }
        return upbitApiCaller.getCoinPrice200(coin.market)
    }

}