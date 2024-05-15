package coinkiri.api.service.auth

import coinkiri.api.client.kakao.KakaoApiCaller
import coinkiri.api.controller.auth.dto.SignupDto
import coinkiri.api.service.member.MemberService
import coinkiri.core.domain.member.SocialType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class KakaoAuthService (
    private val kakaoApiCaller: KakaoApiCaller,
    private val memberService: MemberService
) : AuthService {

    companion object {
        private val socialType: SocialType = SocialType.KAKAO
    }

    @Transactional
    override fun signup(request: SignupDto): Long {
        val response = kakaoApiCaller.getProfileInfo(request.token)

    }
}