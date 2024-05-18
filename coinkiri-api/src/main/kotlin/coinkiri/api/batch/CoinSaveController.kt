package coinkiri.api.batch

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Batch")
@RestController
@RequestMapping("/api/v1/batch")
class CoinSaveController (
    private val coinSaveService: CoinSaveService
){
    @Operation(summary = "코인 리스트 저장")
    @PostMapping("/save-coin")
    fun saveCoinList() {
        coinSaveService.saveCoinList()
    }

    @Operation(summary = "코인 이미지 저장")
    @PostMapping("/save-image")
    fun saveCoinImage() {
        coinSaveService.saveCoinImage()
    }
}