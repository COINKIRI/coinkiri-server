package coinkiri.api.service.auth.jwt

import coinkiri.api.controller.auth.dto.response.TokenResponseDto
import org.springframework.stereotype.Service

@Service
class TokenService (
    private val jwtHandler: JwtHandler,
) {
    fun createTokenInfo(memberId: Long): TokenResponseDto {
        val tokens = jwtHandler.createTokenInfo(memberId)
        return tokens.toResponseDto()
    }
}