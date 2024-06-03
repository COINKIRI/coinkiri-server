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

    @Operation(summary = "[인증] 팔로우 추가")
    @PostMapping("/{followMemberId}")
    @Auth
    fun saveFollow(
        @MemberID memberId: Long,
        @PathVariable followMemberId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        followService.saveFollow(memberId, followMemberId)
        log.info { "팔로우 추가 성공 - memberId: $memberId, followId: $followMemberId" }
        return ResponseEntity.ok(ApiResponse.success())
    }

    @Operation(summary = "[인증] 팔로우 삭제")
    @DeleteMapping("/delete/{followMemberId}")
    @Auth
    fun deleteFollow(
        @MemberID memberId: Long,
        @PathVariable followMemberId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        followService.deleteFollow(memberId, followMemberId)
        log.info { "팔로우 삭제 성공 - memberId: $memberId, followId: $followMemberId" }
        return ResponseEntity.ok(ApiResponse.success())
    }

    @Operation(summary = "[인증] 팔로우 여부 확인")
    @GetMapping("/check/{followMemberId}")
    @Auth
    fun checkFollow(
        @MemberID memberId: Long,
        @PathVariable followMemberId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        val isFollow = followService.checkFollow(memberId, followMemberId)
        log.info { "팔로우 여부 확인 성공 - memberId: $memberId, followId: $followMemberId" }
        return ResponseEntity.ok(ApiResponse.success(isFollow))
    }

    @Operation(summary = "[인증] 팔로우 목록 조회")
    @GetMapping("/following")
    @Auth
    fun findFollowingList(
        @MemberID memberId: Long
    ) : ResponseEntity<ApiResponse<Any>> {
        val followingList = followService.findFollowingList(memberId)
        log.info { "팔로잉 목록 조회 성공 - memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success(followingList))
    }

    @Operation(summary = "[인증] 팔로워 목록 조회")
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