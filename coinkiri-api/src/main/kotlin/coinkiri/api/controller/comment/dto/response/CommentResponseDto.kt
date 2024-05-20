package coinkiri.api.controller.comment.dto.response

import coinkiri.api.controller.member.dto.response.MemberInfoDto
import coinkiri.api.controller.post.dto.response.PostResponseDto
import java.time.LocalDateTime

class CommentResponseDto (
    val id: Long,
    val post: PostResponseDto,
    val member: MemberInfoDto,
    val content: String,
    val createdAt: LocalDateTime,
)