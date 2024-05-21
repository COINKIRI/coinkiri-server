package coinkiri.api.controller.coin.dto.response


data class CoinResponseDto(
    val id: Long,
    val market: String,
    val koreanName: String,
    val englishName: String,
    val symbolImage: ByteArray,
)