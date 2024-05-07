package coinkiri.api.service.auth

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

@NoArgsConstructor
class CustomOAuth2User(
    private val socialId: String
) : OAuth2User{

    override fun getAttributes(): MutableMap<String, Any>? {
        return null
    }

    override fun getName(): String {
        return this.socialId
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null
    }

}