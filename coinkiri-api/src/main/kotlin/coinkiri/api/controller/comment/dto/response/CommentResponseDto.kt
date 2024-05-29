package coinkiri.api.controller.comment.dto.response

import coinkiri.api.controller.member.dto.response.MemberResponseDto
import java.time.LocalDateTime

class CommentResponseDto (
    val id: Long,
    val content: String,
    val createdAt: String,
    val modifiedAt: String,
    val member: MemberResponseDto
)