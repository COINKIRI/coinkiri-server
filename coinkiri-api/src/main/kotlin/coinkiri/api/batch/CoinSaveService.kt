package coinkiri.api.batch

import coinkiri.common.KotlinLogging.log
import coinkiri.core.domain.coin.Coin
import coinkiri.core.domain.coin.repository.CoinRepository
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import java.io.File
import java.util.*

@Service
class CoinSaveService (
    private val coinRepository: CoinRepository
){
    @Transactional
    fun saveCoinList() {
        val upbitApiUrl = "https://api.upbit.com/v1/market/all"
        val restTemplate = RestTemplate()

        val response: ResponseEntity<List<Map<String, Any>>> = restTemplate.exchange(
            upbitApiUrl,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<Map<String, Any>>>() {}
        )

        val coins = coinRepository.findAll()
        val itemList = response.body ?: emptyList()
        itemList.forEach {item ->
            val market = item["market"] as String
            val koreanName = item["korean_name"] as String
            val englishName = item["english_name"] as String

            if(market.startsWith("KRW-")) {
                val coin = coins.find { it.market == market }
                if(coin == null) { // 코인이 없으면 저장
                    coinRepository.save(
                        Coin(market.substring(4), koreanName, englishName)
                    )
                }
            }
        }
    }

    @Transactional
    fun saveCoinImage() {

        val folder = File("src/main/resources/symbol_images_png")
        val files = folder.listFiles()

        val coins = coinRepository.findAll()

        files?.forEach { file ->
            val fileName = file.name
            log.info { "fileName: $fileName" }
            val formattedFileName = fileName.substring(0, fileName.indexOf(".")).uppercase()

            val coin = coins.find { it.market == formattedFileName }

            if (coin != null) {
                if(coin.symbolImage == null) {
                    val symbolImage = pngToByteConverter(file)
                    coin.updateSymbolImage(symbolImage)
                    coinRepository.save(coin)
                }
            }
        }
    }

    private fun pngToByteConverter(file: File): ByteArray {
        val bytes = file.readBytes()
        return Base64.getEncoder().encode(bytes)
    }


}