package coinkiri.api.controller.coin

import coinkiri.api.service.coin.CoinService
import coinkiri.common.response.ApiResponse
import coinkiri.core.domain.coin.Coin
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @ClassName    : CoinController
 *
 */
@Tag(name = "Coin")
@RestController
@RequestMapping("/api/v1/coin")
class CoinController (
    private val coinService: CoinService
) {

    @Operation(summary = "코인 리스트 조회")
    @GetMapping("/all")
    fun findCoinList() : List<Coin> {
        return coinService.findCoinList()
    }



}