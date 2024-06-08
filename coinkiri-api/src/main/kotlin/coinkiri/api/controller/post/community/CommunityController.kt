package coinkiri.api.controller.post.community

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.config.resolver.MemberID
import coinkiri.api.controller.post.dto.request.CommunityRequestDto
import coinkiri.api.controller.post.dto.request.PostRequestDto
import coinkiri.api.service.post.CommunityService
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Community")
@RestController
@RequestMapping("/api/v1/post/community")
class CommunityController (
    private val communityService: CommunityService
){

    // 커뮤니티 글 작성 API
    @Auth
    @Operation(summary = "[인증] 커뮤니티 글 작성")
    @PostMapping("/save")
    fun saveCommunityPost(
        @MemberID memberId: Long,
        @RequestBody request: PostRequestDto
    ): ResponseEntity<ApiResponse<Any>> {
        communityService.saveCommunityPost(memberId, request)
        log.info { "커뮤니티 글 작성 완료" }
        return ResponseEntity.ok(ApiResponse.success())
    }

    // 커뮤니티 전체 조회 API
    @Operation(summary = "커뮤니티 전체 조회")
    @GetMapping("/all")
    fun findAllCommunity(): ResponseEntity<ApiResponse<Any>> {
        val posts = communityService.findAllCommunity()
        log.info { "커뮤니티 전체 조회 완료." }
        return ResponseEntity.ok(ApiResponse.success(posts))
    }

    // 커뮤니티 상세 조회 API
    @Operation(summary = "커뮤니티 상세 조회")
    @GetMapping("/{postId}")
    fun findCommunityDetail(
        @PathVariable postId: Long
    ): ResponseEntity<ApiResponse<Any>> {
        val post = communityService.findCommunityDetail(postId)
        log.info { "커뮤니티 상세 조회 완료." }
        return ResponseEntity.ok(ApiResponse.success(post))
    }

    // 유저 작성 글 조회 API
    @Operation(summary = "[인증] 유저 작성 글 조회")
    @GetMapping("/member")
    @Auth
    fun findMemberPost(
        @MemberID memberId: Long
    ): ResponseEntity<ApiResponse<Any>> {
        val posts = communityService.findMemberPost(memberId)
        log.info { "유저 작성 글 조회 완료." }
        return ResponseEntity.ok(ApiResponse.success(posts))
    }

}