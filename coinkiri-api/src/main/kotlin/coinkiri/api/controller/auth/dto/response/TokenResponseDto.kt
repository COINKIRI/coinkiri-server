package coinkiri.api.controller.auth.dto.response


data class TokenResponseDto(

    val accessToken: String,

    val refreshToken: String
)
