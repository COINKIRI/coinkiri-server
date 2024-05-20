package coinkiri.api.controller.auth.dto.request

import coinkiri.api.controller.member.dto.request.MemberRequestDto

data class SignupRequestDto(
    val token: String, // 소셜 토큰
    val socialType: String, // 소셜 로그인 타입
) {
    fun toRegisterRequestDto(socialId: String): MemberRequestDto {
        return MemberRequestDto(
            socialId,
            socialType
        )
    }
}
