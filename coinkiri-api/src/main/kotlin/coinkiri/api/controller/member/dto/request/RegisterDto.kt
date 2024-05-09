package coinkiri.api.controller.member.dto.request

import coinkiri.core.domain.member.Member

data class RegisterDto(
    val socialId: String,
    val socialType: String
) {
    fun toEntity(): Member {
        return Member(socialId, socialType)
    }
}
