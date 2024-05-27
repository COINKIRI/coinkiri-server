package coinkiri.api.controller.like

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.config.resolver.MemberID
import coinkiri.api.service.like.LikeService
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
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

    @Operation(summary = "좋아요 추가, 삭제")
    @PostMapping("/{postId}")
    @Auth
    fun updateLike(
        @MemberID memberId: Long,
        @PathVariable postId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        likeService.updateLike(memberId, postId)
        log.info { "좋아요 업데이트 성공 - memberId: $memberId, postId: $postId" }
        return ResponseEntity.ok(ApiResponse.success())
    }
}