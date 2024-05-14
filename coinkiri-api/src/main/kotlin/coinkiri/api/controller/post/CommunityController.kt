package coinkiri.api.controller.post

import coinkiri.api.controller.post.dto.request.CommunityRequestDto
import coinkiri.api.service.post.CommunityService
import coinkiri.common.response.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/post")
class CommunityController (
    private val communityService: CommunityService
){

    // 커뮤니티 글 작성 API
    @PostMapping("/community/save")
    fun saveCommunityPost(@RequestBody request: CommunityRequestDto): ResponseEntity<ApiResponse<Any>> {
        communityService.saveCommunityPost(request)
        return ResponseEntity.ok(ApiResponse.success())
    }

    // 커뮤니티 글 조회 API
    @PostMapping("/community/{postId}")
    fun findCommunityPost(@PathVariable postId: Long): ResponseEntity<ApiResponse<Any>> {
        val post = communityService.findCommunityPost(postId)
        return ResponseEntity.ok(ApiResponse.success(post))
    }
}