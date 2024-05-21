package coinkiri.api.controller.coin.dto.response


data class PriceDto( // 1일 가격
    val candleDateTimeKst: String,
    val tradePrice: Double
)

data class CoinPriceDto( // 200일 가격
    val market: String,
    val coinPrices: List<PriceDto>
)

data class CoinPricesDto( // 여러 코인의 200일 가격
    val coinPrices: List<CoinPriceDto>
)