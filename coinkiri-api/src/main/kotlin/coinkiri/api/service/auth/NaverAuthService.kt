package coinkiri.api.service.auth

import coinkiri.api.controller.auth.dto.request.LoginRequestDto
import coinkiri.api.controller.auth.dto.request.SignupRequestDto
import org.springframework.stereotype.Service

@Service
class NaverAuthService (

): AuthService {

    override fun signup(request: SignupRequestDto): Long {
        return 0
    }

    override fun login(request: LoginRequestDto): Long {
        return 0
    }
}