package coinkiri.api.service.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class OAuth2SuccessHandler (
    private val jwtProvider: JwtProvider
) : SimpleUrlAuthenticationSuccessHandler() {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {

        val oAuth2User: CustomOAuth2User = authentication.principal as CustomOAuth2User

        val socialId = oAuth2User.name
        val token = jwtProvider.createJwt(socialId) // jwt 생성

        response.sendRedirect("http://localhost:3000/oauth2/redirect?token=$token/3600") // 리다이렉트

    }
}