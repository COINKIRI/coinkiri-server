package coinkiri.api.controller.auth

import coinkiri.api.controller.auth.dto.request.SignupRequestDto
import coinkiri.api.service.auth.AuthServiceProvider
import coinkiri.api.service.auth.jwt.TokenService
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import coinkiri.core.domain.member.SocialType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Auth")
@RestController
@RequestMapping("/api/v1/auth")
class AuthController (
    private val authServiceProvider: AuthServiceProvider,
    private val tokenService: TokenService
){
    @Operation(summary = "소셜 회원가입")
    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequestDto): ResponseEntity<ApiResponse<Any>> {
        val authService = authServiceProvider.getAuthService(SocialType.valueOf(request.socialType))
        val memberId = authService.signup(request)
        val jwtToken = tokenService.createTokenInfo(memberId)
        log.info { "소셜 회원가입 완료. memberId: $memberId, jwtToken: $jwtToken" }
        return ResponseEntity.ok(ApiResponse.success(jwtToken))
    }
}