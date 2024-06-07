package coinkiri.api.controller.interest

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.config.resolver.MemberID
import coinkiri.api.service.interest.InterestService
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
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

    @Operation(summary = "[인증] 관심 등록 여부")
    @GetMapping("/check/{coinId}")
    @Auth
    fun checkInterest(
        @PathVariable coinId: Long,
        @MemberID memberId: Long
    ): ResponseEntity<ApiResponse<Any>> {
        val isInterest = interestService.checkInterest(memberId, coinId)
        log.info { "관심 등록 여부 조회 완료. memberId: $memberId, coinId: $coinId" }
        return ResponseEntity.ok(ApiResponse.success(isInterest))
    }

    @Operation(summary = "[인증] 관심 종목 삭제")
    @DeleteMapping("/delete/{coinId}")
    @Auth
    fun deleteInterest(
        @PathVariable coinId: Long,
        @MemberID memberId: Long
    ): ResponseEntity<ApiResponse<Any>> {
        interestService.deleteInterest(memberId, coinId)
        return ResponseEntity.ok(ApiResponse.success())
    }

    @Operation(summary = "[인증] 관심 종목 리스트 조회")
    @GetMapping("/")
    @Auth
    fun findInterestList(
        @MemberID memberId: Long
    ): ResponseEntity<ApiResponse<Any>> {
        val coinPrices = interestService.findInterestList(memberId)
        log.info { "관심 종목 조회 완료. memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success(coinPrices))
    }

    @Operation(summary = "[인증] 관심 종목 분석글 조회")
    @GetMapping("/analysis")
    @Auth
    fun findInterestAnalysis(
        @MemberID memberId: Long
    ): ResponseEntity<ApiResponse<Any>> {
        val analysis = interestService.findInterestAnalysis(memberId)
        log.info { "관심 종목 분석글 조회 완료. memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success(analysis))
    }


}