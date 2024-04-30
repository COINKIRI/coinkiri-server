package coinkiri.api.controller.coin

import coinkiri.api.service.coin.CoinService
import coinkiri.core.domain.coin.Coin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @ClassName    : CoinController
 *
 */
@RestController
@RequestMapping("/coin")
class CoinController (
    private val coinService: CoinService
) {
    @GetMapping("/all")
    fun findCoinList() : List<Coin> {
        return coinService.findCoinList()
    }
}