package coinkiri.api.controller.member.dto.request

import coinkiri.core.domain.member.SocialType


data class RegisterDto(
    val socialId: String,
    val socialType: SocialType,
)
