package coinkiri.api.controller.post.analysis

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.config.resolver.MemberID
import coinkiri.api.controller.post.dto.request.AnalysisRequestDto
import coinkiri.api.service.post.AnalysisService
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Analysis")
@RestController
@RequestMapping("/api/v1/analysis")
class AnalysisController (
    private val analysisService: AnalysisService
){

    // 분석글 작성 API
    @Auth
    @Operation(summary = "[인증] 분석글 작성")
    @PostMapping("/save")
    fun saveAnalysisPost(
        @MemberID memberId: Long,
        @RequestBody request: AnalysisRequestDto
    ): ResponseEntity<ApiResponse<Any>> {
        analysisService.saveAnalysisPost(memberId, request)
        log.info { "분석글 작성 완료" }
        return ResponseEntity.ok(ApiResponse.success())
    }

//    // 분석글 전체 조회 API
//    @Operation(summary = "분석글 전체 조회")
//    @GetMapping("/all")
//    fun findAllAnalysis(): ResponseEntity<ApiResponse<Any>> {
//        val analysis = analysisService.findAllAnalysis()
//        log.info { "분석글 전체 조회 완료." }
//        return ResponseEntity.ok(ApiResponse.success(analysis))
//    }
//
//    // 분석글 상세 조회 API
//    @Operation(summary = "분석글 상세 조회")
//    @GetMapping("/{postId}")
//    fun findAnalysisDetail(
//        @PathVariable postId: Long
//    ): ResponseEntity<ApiResponse<Any>> {
//        val analysis = analysisService.findAnalysisDetail(postId)
//        log.info { "분석글 상세 조회 완료." }
//        return ResponseEntity.ok(ApiResponse.success(analysis))
//    }


}