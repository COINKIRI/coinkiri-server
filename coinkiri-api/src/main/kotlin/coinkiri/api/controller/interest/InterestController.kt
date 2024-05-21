package coinkiri.api.controller.interest

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.config.resolver.MemberID
import coinkiri.api.service.interest.InterestService
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Interest")
@RestController
@RequestMapping("/api/v1/interest")
class InterestController (
    private val interestService: InterestService
){

    @Operation(summary = "[인증] 관심 종목 등록")
    @PostMapping("/{coinId}")
    @Auth
    fun saveInterest(
        @PathVariable coinId: Long,
        @MemberID memberId: Long
    ): ResponseEntity<ApiResponse<Any>> {
        interestService.saveInterest(memberId, coinId)
        return ResponseEntity.ok(ApiResponse.success())
    }
}