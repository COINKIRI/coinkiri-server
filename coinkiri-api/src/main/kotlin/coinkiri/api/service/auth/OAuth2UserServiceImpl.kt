package coinkiri.api.service.auth

import coinkiri.core.domain.member.Member
import coinkiri.core.domain.member.repository.MemberRepository
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
@Transactional
class OAuth2UserServiceImpl(
    private val memberRepository: MemberRepository
) : DefaultOAuth2UserService() {

    // oauth2 로그인 시 request에서 사용자 정보를 가져오는 메소드
    override fun loadUser(request: OAuth2UserRequest): OAuth2User {

        val oAuth2User = super.loadUser(request)
        val oauthClientName = request.clientRegistration.clientName // oauth2 client 이름 (kakao, naver)

        try {
            println(ObjectMapper().writeValueAsString(oAuth2User.attributes))
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        lateinit var member: Member
        lateinit var socialId: String

        if (oauthClientName == "kakao") {
            socialId = "kakao_" + oAuth2User.attributes["id"]
            member = Member(socialId, "KAKAO") // kakao 로그인 시 member 생성
        }

        if (oauthClientName == "naver") {
            val responseMap = oAuth2User.attributes["response"] as Map<*, *>
            val id = responseMap["id"] as String // id 값 가져오기
            socialId = "naver_" + id.substring(0, 14)
            member = Member(socialId, "NAVER") // SocialType.NAVER 열거형 값 사용
        }

        memberRepository.save(member) // member 저장

        return CustomOAuth2User(socialId) // 사용자 정보 반환 -> Token 생성 (OAuth2SuccessHandler)
    }
}