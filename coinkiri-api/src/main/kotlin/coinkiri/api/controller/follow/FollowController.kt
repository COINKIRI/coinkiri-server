package coinkiri.api.controller.follow

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.config.resolver.MemberID
import coinkiri.api.service.follow.FollowService
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Follow")
@RestController
@RequestMapping("/api/v1/follow")
class FollowController (
    private val followService: FollowService
){

    @Operation(summary = "팔로우 추가/삭제")
    @PostMapping("/{followId}")
    @Auth
    fun saveFollow(
        @MemberID memberId: Long,
        @PathVariable followId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        followService.updateFollow(memberId, followId)
        log.info { "팔로우 추가, 삭제 성공 - memberId: $memberId, followId: $followId" }
        return ResponseEntity.ok(ApiResponse.success())
    }

    @Operation(summary = "팔로우 목록 조회")
    @GetMapping("/following")
    @Auth
    fun findFollowingList(
        @MemberID memberId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        val followingList = followService.findFollowingList(memberId)
        log.info { "팔로잉 목록 조회 성공 - memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success(followingList))
    }

    @Operation(summary = "팔로워 목록 조회")
    @GetMapping("/follower")
    @Auth
    fun findFollowerList(
        @MemberID memberId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        val followerList = followService.findFollowerList(memberId)
        log.info { "팔로워 목록 조회 성공 - memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success(followerList))
    }


}