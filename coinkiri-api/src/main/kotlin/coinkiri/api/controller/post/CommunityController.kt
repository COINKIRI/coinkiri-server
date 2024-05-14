package coinkiri.api.controller.post

import coinkiri.api.controller.post.dto.request.CommunityRequestDto
import coinkiri.api.service.post.CommunityService
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Community")
@RestController
@RequestMapping("/api/v1/post")
class CommunityController (
    private val communityService: CommunityService
){

    // 커뮤니티 글 작성 API
    @Operation(summary = "커뮤니티 글 작성")
    @PostMapping("/community/save")
    fun saveCommunityPost(@RequestBody request: CommunityRequestDto): ResponseEntity<ApiResponse<Any>> {
        communityService.saveCommunityPost(request)
        return ResponseEntity.ok(ApiResponse.success())
    }

    // 커뮤니티 글 조회 API
    @Operation(summary = "커뮤니티 글 조회")
    @PostMapping("/community/{postId}")
    fun findCommunityPost(@PathVariable postId: Long): ResponseEntity<ApiResponse<Any>> {
        val post = communityService.findCommunityPost(postId)
        log.info { "커뮤니티 글 조회 완료. postId: $postId" }
        return ResponseEntity.ok(ApiResponse.success(post))
    }

}