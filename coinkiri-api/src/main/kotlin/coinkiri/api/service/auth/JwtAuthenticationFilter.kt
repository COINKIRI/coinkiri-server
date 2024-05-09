package coinkiri.api.service.auth

import coinkiri.core.domain.member.repository.MemberRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
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
    private val memberRepository: MemberRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {

            val token = parseBearerToken(request)
            if (token == null) { // token 값이 없으면
                filterChain.doFilter(request, response)
                return
            }

            val userId = jwtProvider.validateJwt(token) // token 값이 유효한지 검증
            if (userId == null) { // 유효하지 않으면
                filterChain.doFilter(request, response)
                return
            }

            val user = memberRepository.findBySocialId(userId) // 유효한 token 값으로 user 정보를 가져옴

            // SecurityContext 생성 (SecurityContextHolder는 Bean으로 등록되어 있음)
            val securityContext = SecurityContextHolder.createEmptyContext()

            val authenticationToken = UsernamePasswordAuthenticationToken(userId, null) // AuthenticationToken 생성
            authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request) // AuthenticationToken에 request 정보를 추가
            securityContext.authentication = authenticationToken // SecurityContext에 AuthenticationToken을 추가

            SecurityContextHolder.setContext(securityContext)
            // SecurityContext를 SecurityContextHolder에 추가


        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        filterChain.doFilter(request, response)
    }

    // Request Header에서 Bearer token 값을 추출함
    fun parseBearerToken(request: HttpServletRequest): String? {
        val authorization: String =
            request.getHeader("Authorization") ?: return null // Request Header에서 꺼낸 Bearer token 값
        // 값이 없으면 null 반환

        val hasAuthorization: Boolean = StringUtils.hasText(authorization) // 값이 있는지 확인
        if (!hasAuthorization) return null // 값이 없으면 null 반환

        val isBearer: Boolean = authorization.startsWith("Bearer ") // Bearer로 시작하는지 확인
        if (!isBearer) return null // Bearer로 시작하지 않으면 null 반환

        val token: String = authorization.substring(7) // Bearer 뒤에 있는 token 값만 추출
        return token
    }
}