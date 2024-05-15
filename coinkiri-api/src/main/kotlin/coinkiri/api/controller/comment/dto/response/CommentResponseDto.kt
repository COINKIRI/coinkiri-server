package coinkiri.api.controller.comment.dto.response

import coinkiri.api.controller.member.dto.response.MemberInfoDto

class CommentResponseDto (
    val parentComment: CommentResponseDto?,
    val member: MemberInfoDto,
    val content: String,
)