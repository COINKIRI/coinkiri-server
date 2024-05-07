package coinkiri.api.service.auth

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class OAuth2UserServiceImplement : DefaultOAuth2UserService() {

    override fun loadUser(request: OAuth2UserRequest): OAuth2User {
        val oAuth2User = super.loadUser(request)

        try {
            println(ObjectMapper().writeValueAsString(oAuth2User.attributes))
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        return oAuth2User
    }
}