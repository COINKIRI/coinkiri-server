package coinkiri.api.controller.talk

import coinkiri.api.controller.member.dto.response.MemberResponseDto


data class TalkResponseDto (
    val title: String,
    val content: String,
    val createdAt: String,
    val member: MemberResponseDto
)