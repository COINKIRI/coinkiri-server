package coinkiri.api.controller.comment.dto.response

import coinkiri.api.controller.member.dto.response.MemberInfoDto
import java.time.LocalDateTime

class CommentResponseDto (
    val id: Long,
    val content: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
    val member: MemberInfoDto
)