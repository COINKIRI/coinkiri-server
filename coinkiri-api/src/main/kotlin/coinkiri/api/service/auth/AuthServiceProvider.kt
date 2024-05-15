package coinkiri.api.service.auth

import coinkiri.core.domain.member.SocialType
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class AuthServiceProvider (
    private val naverAuthService: NaverAuthService,
    private val kakaoAuthService: KakaoAuthService
){
    companion object {
        val authServiceMap = mutableMapOf<SocialType, AuthService>()
    }

    @PostConstruct
    fun initAuthServiceMap() {
        authServiceMap[SocialType.KAKAO] = kakaoAuthService
        authServiceMap[SocialType.NAVER] = naverAuthService
    }

    fun getAuthService(socialType: SocialType): AuthService {
        return authServiceMap[socialType]!!
    }
}