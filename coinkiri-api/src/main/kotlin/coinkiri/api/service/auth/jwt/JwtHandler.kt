package coinkiri.api.service.auth.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtHandler (

) {
    @Value("\${jwt.secret}")
    private var jwtSecret: String? = null
    private var secretKey: Key? = null

    companion object {
        // private const val ACCESS_TOKEN_EXPIRE_TIME = 10 * 60 * 1000L // 10분
        // private const val REFRESH_TOKEN_EXPIRE_TIME = 6 * 30 * 24 * 60 * 60 * 1000L // 180일
        private const val ACCESS_TOKEN_EXPIRE_TIME = 365 * 24 * 60 * 60 * 1000L;   // 1년
        private const val REFRESH_TOKEN_EXPIRE_TIME = 365 * 24 * 60 * 60 * 1000L;    // 1년
        private const val EXPIRED_TIME = 1L
    }

    @PostConstruct
    fun init() { // jwtSecret을 Base64로 디코딩하여 secretKey를 생성
        val keyBytes: ByteArray = Decoders.BASE64.decode(jwtSecret)
        this.secretKey = Keys.hmacShaKeyFor(keyBytes)
    }

    fun createTokenInfo(memberId: Long): TokenDto {
        val now = Date().time
        val accessTokenExpiresIn = Date(now + ACCESS_TOKEN_EXPIRE_TIME)
        val refreshTokenExpiresIn = Date(now + REFRESH_TOKEN_EXPIRE_TIME)

        // Access Token 생성
        val accessToken: String = Jwts.builder()
            .claim(JwtKey.MEMBER_ID, memberId)
            .setExpiration(accessTokenExpiresIn)
            .signWith(secretKey, SignatureAlgorithm.HS512)
            .compact()

        // Refresh Token 생성
        val refreshToken: String = Jwts.builder()
            .setExpiration(refreshTokenExpiresIn)
            .signWith(secretKey, SignatureAlgorithm.HS512)
            .compact()

        return TokenDto(accessToken, refreshToken)
    }
}