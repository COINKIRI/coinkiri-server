package coinkiri.api.controller.like

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.config.resolver.MemberID
import coinkiri.api.service.like.LikeService
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

@Tag(name = "Like")
@RestController
@RequestMapping("/api/v1/like")
class LikeController (
    private val likeService: LikeService
){

    @Operation(summary = "[인증] 좋아요 추가")
    @PostMapping("/{postId}")
    @Auth
    fun updateLike(
        @MemberID memberId: Long,
        @PathVariable postId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        likeService.saveLike(memberId, postId)
        return ResponseEntity.ok(ApiResponse.success())
    }

    @Operation(summary = "[인증] 좋아요 삭제")
    @DeleteMapping("/delete/{postId}")
    @Auth
    fun deleteLike(
        @MemberID memberId: Long,
        @PathVariable postId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        likeService.deleteLike(memberId, postId)
        return ResponseEntity.ok(ApiResponse.success())
    }

    @Operation(summary = "[인증] 좋아요 여부 확인")
    @GetMapping("/check/{postId}")
    @Auth
    fun checkLike(
        @MemberID memberId: Long,
        @PathVariable postId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        val isLike = likeService.checkLike(memberId, postId)
        log.info { "좋아요 여부 확인 성공 - memberId: $memberId, postId: $postId" }
        return ResponseEntity.ok(ApiResponse.success(isLike))
    }

    @Operation(summary = "[인증] 좋아요한 커뮤니티 글 조회")
    @GetMapping("/community")
    @Auth
    fun getLikeCommunity(
        @MemberID memberId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        val likeCommunity = likeService.findLikeCommunity(memberId)
        log.info { "좋아요한 커뮤니티 글 조회 성공 - memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success(likeCommunity))
    }

    @Operation(summary = "[인증] 좋아요한 분석 글 조회")
    @GetMapping("/analysis")
    @Auth
    fun getLikeAnalysis(
        @MemberID memberId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        val likeAnalysis = likeService.findLikeAnalysis(memberId)
        log.info { "좋아요한 분석 글 조회 성공 - memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success(likeAnalysis))
    }
}