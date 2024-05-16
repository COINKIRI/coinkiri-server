package coinkiri.api.batch

import coinkiri.core.domain.coin.Coin
import coinkiri.core.domain.coin.repository.CoinRepository
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange

@Service
class CoinSaveService (
    private val coinRepository: CoinRepository
){
    @Transactional
    fun saveCoinList() {
        val UPBIT_API_URL = "https://api.upbit.com/v1/market/all"
        val restTemplate = RestTemplate()

        val response: ResponseEntity<List<Map<String, Any>>> = restTemplate.exchange(
            UPBIT_API_URL,
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
                        Coin(market, koreanName, englishName)
                    )
                }
            }
        }
    }


}