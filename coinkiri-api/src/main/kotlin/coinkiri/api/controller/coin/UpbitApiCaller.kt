package coinkiri.api.controller.coin

import coinkiri.api.controller.coin.dto.response.CoinPriceDto
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class UpbitApiCaller (
    private val restTemplate: RestTemplate
){
    fun getCoinPrice200(market: String): List<CoinPriceDto> {
        val url = "https://api.upbit.com/v1/candles/days?market=KRW-$market&count=200"

        // REST API 호출
        val response = restTemplate.getForObject(url, Array<CoinPriceResponse>::class.java)
            ?: throw RuntimeException("Upbit API 응답이 null입니다.")


        // CoinPriceResponse를 CoinPriceDto로 변환
        return response.map { it.toCoinPriceDto() }
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
    ) {
        // CoinPriceDto로 변환하는 확장 함수
        fun toCoinPriceDto(): CoinPriceDto {
            return CoinPriceDto(
                market = this.market,
                candleDateTimeKst = this.candle_date_time_kst,
                tradePrice = this.trade_price,
            )
        }
    }
}
