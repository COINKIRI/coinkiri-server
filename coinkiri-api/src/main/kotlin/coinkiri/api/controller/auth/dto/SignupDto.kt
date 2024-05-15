package coinkiri.api.controller.auth.dto

import coinkiri.core.domain.member.SocialType

data class SignupDto(

    val socialType: SocialType, // 소셜 로그인 타입

    val token: String, // 소셜 토큰
)
