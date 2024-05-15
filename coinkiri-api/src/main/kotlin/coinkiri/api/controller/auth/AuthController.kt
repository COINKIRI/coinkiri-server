package coinkiri.api.controller.auth

import coinkiri.api.controller.auth.dto.SignupRequestDto
import coinkiri.api.service.auth.AuthServiceProvider
import coinkiri.core.domain.member.SocialType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Auth")
@RestController
@RequestMapping("/api/v1/auth")
class AuthController (
    private val authServiceProvider: AuthServiceProvider
){
    @Operation(summary = "소셜 회원가입")
    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequestDto): Long {
        val authService = authServiceProvider.getAuthService(SocialType.valueOf(request.socialType))
        val memberId = authService.signup(request)
        return memberId
    }
}