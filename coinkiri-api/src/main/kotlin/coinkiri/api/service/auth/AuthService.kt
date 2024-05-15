package coinkiri.api.service.auth

import coinkiri.api.controller.auth.dto.SignupDto

interface AuthService {

    fun signup(request: SignupDto): Long



}