package coinkiri.api.config.interceptor

import coinkiri.api.service.auth.jwt.JwtHandler
import coinkiri.common.KotlinLogging.log
import coinkiri.common.exception.CoinkiriException
import coinkiri.common.exception.ExceptionCode
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component

@Component
class LoginCheckHandler (
    private val jwtHandler: JwtHandler
){
    fun getMemberId(request: HttpServletRequest): Long {
        val bearerToken: String? = request.getHeader("Authorization")
        if (!bearerToken.isNullOrBlank() && bearerToken.startsWith("Bearer ")) {
            val accessToken = bearerToken.substring("Bearer ".length)
            if (jwtHandler.validateToken(accessToken)) {
                return jwtHandler.getMemberIdFromJwt(accessToken)
            }
        }
        throw CoinkiriException(HttpStatusCode.valueOf(401), "잘못된 JWT $bearerToken 입니다.")
    }
}