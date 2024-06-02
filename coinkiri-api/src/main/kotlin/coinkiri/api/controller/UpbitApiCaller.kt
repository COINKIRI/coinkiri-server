package coinkiri.api.controller

import coinkiri.api.controller.coin.dto.response.CoinPriceDto
import coinkiri.api.controller.coin.dto.response.CoinPricesDto
import coinkiri.api.controller.coin.dto.response.PriceDto
import coinkiri.common.KotlinLogging.log
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class UpbitApiCaller (
    private val restTemplate: RestTemplate
){
    // 200일치 코인 가격 조회 (단일)
    fun getCoinPrice200(market: String): CoinPriceDto {
        val url = "https://api.upbit.com/v1/candles/days?market=KRW-$market&count=200"

        // REST API 호출
        val response = restTemplate.getForObject(url, Array<CoinPriceResponse>::class.java)
            ?: throw RuntimeException("Upbit API 응답이 null입니다.")

        // CoinPriceResponse를 PriceDto로 변환하고, CoinPriceDto로 변환
        val priceDtos = response.map { PriceDto(it.candle_date_time_kst, it.trade_price) }
        return CoinPriceDto(market = "KRW-$market", coinPrices = priceDtos)
    }

    // 200일치 코인 가격 조회 (다수)
    fun getCoinPrices200(marketList: List<String>) : CoinPricesDto {
        val coinPrices = marketList.map { getCoinPrice200(it) }
        return CoinPricesDto(coinPrices)
    }

    // 24시간(1시간 단위) 코인 가격 조회
    fun getCoinPrice24(market: String): CoinPriceDto {
        val url = "https://api.upbit.com/v1/candles/minutes/60?market=KRW-$market&count=24"

        // REST API 호출
        val response = restTemplate.getForObject(url, Array<CoinPriceResponse>::class.java)
            ?: throw RuntimeException("Upbit API 응답이 null입니다.")

        // CoinPriceResponse를 PriceDto로 변환하고, CoinPriceDto로 변환
        val priceDtos = response.map { PriceDto(it.candle_date_time_kst, it.trade_price) }
        return CoinPriceDto(market = "KRW-$market", coinPrices = priceDtos)
    }

    // 24시간(1시간 단위) 코인 가격 조회 (다수)
    fun getCoinPrices24(marketList: List<String>) : CoinPricesDto {
        val coinPrices = marketList.map { getCoinPrice24(it) }
        return CoinPricesDto(coinPrices)
    }

    // REST API 응답을 파싱할 데이터 클래스
    data class CoinPriceResponse(
        val market: String,
        val candle_date_time_utc: String,
        val candle_date_time_kst: String,
        val opening_price: Double,
        val high_price: Double,
        val low_price: Double,
        val trade_price: Double,
        val timestamp: Long,
        val candle_acc_trade_price: Double,
        val candle_acc_trade_volume: Double,
        val prev_closing_price: Double,
        val change_price: Double,
        val change_rate: Double
    )


}
