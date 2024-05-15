package coinkiri.api.controller.auth.dto

import coinkiri.api.controller.member.dto.request.RegisterRequestDto
import coinkiri.core.domain.member.SocialType

data class SignupRequestDto(
    val socialType: SocialType, // 소셜 로그인 타입
    val token: String, // 소셜 토큰
) {
    fun toRegisterRequestDto(socialId: String): RegisterRequestDto {
        return RegisterRequestDto(
            socialId,
            socialType.toString()
        )
    }
}
