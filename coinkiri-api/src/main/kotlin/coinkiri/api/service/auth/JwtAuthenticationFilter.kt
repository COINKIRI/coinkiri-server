package coinkiri.api.service.auth

import coinkiri.core.domain.user.repository.UserRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

/**
 * @ClassName    : $ 클래스에 대한 설명을 작성합니다.
 *
 */
@Component
class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider,
    private val userRepository: UserRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        try {

            val token = parseBearerToken(request)
            if (token == null) { // token 값이 없으면
                filterChain.doFilter(request, response)
                return
            }

            val userId = jwtProvider.validate(token) // token 값이 유효한지 검증
            if (userId == null) { // 유효하지 않으면
                filterChain.doFilter(request, response)
                return
            }



        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        filterChain.doFilter(request, response)
    }

    // Request Header에서 Bearer token 값 추출 메소드
    fun parseBearerToken(request: HttpServletRequest): String? {
        val authorization: String = request.getHeader("Authorization") // Request Header에서 꺼낸 Bearer token 값

        val hasAuthorization: Boolean = StringUtils.hasText(authorization) // 값이 있는지 확인
        if (!hasAuthorization) return null // 값이 없으면 null 반환

        val isBearer: Boolean = authorization.startsWith("Bearer ") // Bearer로 시작하는지 확인
        if (!isBearer) return null // Bearer로 시작하지 않으면 null 반환

        val token: String = authorization.substring(7) // Bearer 뒤에 있는 token 값만 추출
        return token
    }
}