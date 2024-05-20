package coinkiri.api.controller.post.dto.response

import coinkiri.api.controller.comment.dto.response.CommentResponseDto
import coinkiri.api.controller.member.dto.response.MemberInfoDto
import java.time.LocalDateTime

data class PostResponseDto (
    val id: Long,
    val title: String,
    val content: String,
    val viewCnt: Int,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
    val member: MemberInfoDto,
)

data class CommunityResponseDto (
    val postResponseDto: PostResponseDto,
    val category: String
)