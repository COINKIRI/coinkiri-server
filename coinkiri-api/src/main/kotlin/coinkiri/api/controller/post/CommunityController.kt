package coinkiri.api.controller.post

import coinkiri.api.controller.post.dto.request.CommunityRequestDto
import coinkiri.api.service.post.CommunityService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/post")
class CommunityController (
    private val communityService: CommunityService
){

    // 커뮤니티 글 저장 API
    @PostMapping("/community/save")
    fun saveCommunityPost(@RequestBody request: CommunityRequestDto) {
        communityService.saveCommunityPost(request)
    }
}