package coinkiri.api.controller.post.dto.response

import coinkiri.api.controller.post.dto.request.PostRequestDto
import java.time.LocalDateTime

data class PostResponseDto (
    val id: Long,
    val title: String,
    val content: String,
    val viewCnt: Long,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
    val memberId: Long,
)

data class CommunityResponseDto (
    val postRequestDto: PostRequestDto,
    val category: String
)
