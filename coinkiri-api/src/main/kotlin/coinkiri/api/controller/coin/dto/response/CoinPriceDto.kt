package coinkiri.api.controller.coin.dto.response


data class PriceDto( // 캔들 1개
    val candleDateTimeKst: String,
    val tradePrice: Long
)

data class CoinPriceDto( // 캔들 여러개
    val market: String,
    val coinPrices: List<PriceDto>
)

data class InterestCoinPriceDto( // 캔들 여러개 + 코인 정보
    val coinId: Long,
    val market: String,
    val koreanName: String,
    val symbolImage: ByteArray,
    val coinPrices: List<PriceDto>
)

data class CoinPricesDto( // 여러 코인의 캔들 여러 개
    val coinPrices: List<InterestCoinPriceDto>
)