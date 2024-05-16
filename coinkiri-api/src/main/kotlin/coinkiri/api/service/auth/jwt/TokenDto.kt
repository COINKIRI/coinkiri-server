package coinkiri.api.service.auth.jwt

import coinkiri.api.controller.auth.dto.response.TokenResponseDto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String
) {
    fun toResponseDto(): TokenResponseDto {
        return TokenResponseDto(accessToken, refreshToken)
    }
}
