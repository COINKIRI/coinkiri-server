package coinkiri.api.config.web

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException


class JwtAuthorizationFilter : OncePerRequestFilter() {

    @Throws(IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader("Authorization")

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response)
            return
        }

        val token = header.replace("Bearer ", "")
        val authentication = getAuthentication(token)

        if (authentication != null) {
            SecurityContextHolder.getContext().authentication = authentication
        }

        chain.doFilter(request, response)
    }

    private fun getAuthentication(token: String): UsernamePasswordAuthenticationToken? {
        return try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor("SecretKeyToGenJWTs".toByteArray()))  // your secret key
                .build()
                .parseClaimsJws(token)
                .body

            val user = claims.subject

            if (user != null) {
                UsernamePasswordAuthenticationToken(user, null, emptyList())
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
