package coinkiri.api.controller.auth.dto.request


class LoginRequestDto (
    val token: String, // 소셜 토큰
    val socialType: String, // 소셜 로그인 타입
)