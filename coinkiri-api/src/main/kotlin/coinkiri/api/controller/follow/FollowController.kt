package coinkiri.api.controller.follow

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.config.resolver.MemberID
import coinkiri.api.service.follow.FollowService
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Follow")
@RestController
@RequestMapping("/api/v1/follow")
class FollowController (
    private val followService: FollowService
){

    // 팔로우
    @Operation(summary = "팔로우 하기")
    @PostMapping("/{followId}")
    @Auth
    fun saveFollow(
        @MemberID memberId: Long,
        @PathVariable followId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        followService.saveFollow(memberId, followId)
        log.info { "팔로우 성공 - memberId: $memberId, followId: $followId" }
        return ResponseEntity.ok(ApiResponse.success())
    }

    // 팔로우 취소
    @Operation(summary = "팔로우 취소")
    @DeleteMapping("/{followId}")
    @Auth
    fun deleteFollow(
        @MemberID memberId: Long,
        @PathVariable followId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        followService.deleteFollow(memberId, followId)
        log.info { "팔로우 취소 성공 - memberId: $memberId, followId: $followId" }
        return ResponseEntity.ok(ApiResponse.success())
    }


}