package coinkiri.api.controller.coin

import coinkiri.api.controller.coin.dto.response.CoinResponseDto
import coinkiri.api.service.coin.CoinService
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import coinkiri.core.domain.coin.Coin
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
    fun findCoinList() : ResponseEntity<ApiResponse<List<CoinResponseDto>>> {
        val startTime = System.currentTimeMillis()
        val coinList = coinService.findCoinList()
        val takenTime = System.currentTimeMillis() - startTime

        log.info { "코인 리스트 조회 완료. 실행시간: $takenTime ms" }
        return ResponseEntity.ok(ApiResponse.success(coinList))
    }

    @Operation(summary = "코인 상세 조회 - 200일 차트")
    @GetMapping("/{coinId}")
    fun findCoinDetail(@PathVariable coinId: Long) : ResponseEntity<ApiResponse<Any>> {
        val startTime = System.currentTimeMillis()
        val coinDetail = coinService.findCoinDetail(coinId)
        val takenTime = System.currentTimeMillis() - startTime

        log.info { "코인 상세 조회 완료. 실행시간: $takenTime ms" }
        return ResponseEntity.ok(ApiResponse.success(coinDetail))
    }

}