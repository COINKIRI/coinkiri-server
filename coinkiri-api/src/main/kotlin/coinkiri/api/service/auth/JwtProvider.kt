package coinkiri.api.service.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*


@Component
class JwtProvider {

    @Value("\${jwt.secret}")
    private lateinit var jwtSecret: String

    // jwt 생성
    fun create(userId: String): String {
        val expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)) // 1시간
        val key = Keys.hmacShaKeyFor(jwtSecret.toByteArray(StandardCharsets.UTF_8))

        val jwt = Jwts.builder()
            // jwt header에 저장되는 정보
            .signWith(key, SignatureAlgorithm.HS256)
            // jwt payload에 저장되는 정보
            .setSubject(userId) // sub: jwt 제목
            .setIssuedAt(Date()) // iat: jwt 발급 시간
            .setExpiration(expiredDate) // exp: jwt 만료 시간
            .compact()

        return jwt
    }

    // jwt 검증
    fun validate(jwt: String): String? {

        lateinit var subject: String
        val key: Key = Keys.hmacShaKeyFor(jwtSecret.toByteArray(StandardCharsets.UTF_8))

        try {
            subject = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .body
                .subject

        } catch(exception: Exception) {
            exception.printStackTrace()
            return null
        }

        return ""
    }
}