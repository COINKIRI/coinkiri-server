package coinkiri.api.service.auth

import coinkiri.api.controller.auth.dto.SignupDto
import org.springframework.stereotype.Service

@Service
class NaverAuthService (

): AuthService {

        override fun signup(request: SignupDto): Long {
            return 0
        }
}