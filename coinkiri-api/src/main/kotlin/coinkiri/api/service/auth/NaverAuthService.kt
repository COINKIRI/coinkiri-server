package coinkiri.api.service.auth

import coinkiri.api.controller.auth.dto.SignupRequestDto
import org.springframework.stereotype.Service

@Service
class NaverAuthService (

): AuthService {

    override fun signup(request: SignupRequestDto): Long {
        return 0
    }
}