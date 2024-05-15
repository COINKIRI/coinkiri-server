package coinkiri.api.controller.auth

import coinkiri.api.controller.auth.dto.SignupDto
import coinkiri.api.service.auth.AuthServiceProvider
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
    fun signup(@RequestBody request: SignupDto) {
        val authService = authServiceProvider.getAuthService(request.socialType)
    }
}