package coinkiri.api.service.auth

import coinkiri.api.controller.auth.dto.request.SignupRequestDto

interface AuthService {

    fun signup(request: SignupRequestDto): Long



}