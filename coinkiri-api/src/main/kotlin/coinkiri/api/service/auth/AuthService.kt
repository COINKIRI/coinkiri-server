package coinkiri.api.service.auth

import coinkiri.api.controller.auth.dto.SignupRequestDto

interface AuthService {

    fun signup(request: SignupRequestDto): Long



}