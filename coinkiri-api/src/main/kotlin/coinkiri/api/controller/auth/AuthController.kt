package coinkiri.api.controller.auth

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.config.resolver.MemberID
import coinkiri.api.controller.auth.dto.request.LoginRequestDto
import coinkiri.api.controller.auth.dto.request.SignupRequestDto
import coinkiri.api.controller.auth.dto.response.TokenResponseDto
import coinkiri.api.service.auth.AuthServiceProvider
import coinkiri.api.service.auth.CommonAuthService
import coinkiri.api.service.auth.jwt.TokenService
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import coinkiri.core.domain.member.SocialType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
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
    private val tokenService: TokenService,
    private val commonAuthService: CommonAuthService
){
    @Operation(summary = "소셜 회원등록/인증")
    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequestDto): ResponseEntity<ApiResponse<TokenResponseDto>> {
        val authService = authServiceProvider.getAuthService(SocialType.valueOf(request.socialType))
        val memberId = authService.signup(request)
        val jwtToken = tokenService.createTokenInfo(memberId)
        log.info { "소셜 회원가입 완료. memberId: $memberId, jwtToken: $jwtToken" }
        return ResponseEntity.ok(ApiResponse.success(jwtToken))
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    @Auth
    fun logout(@MemberID memberId: Long): ResponseEntity<ApiResponse<Any>> {
        commonAuthService.logout(memberId)
        log.info { "로그아웃 완료. memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success())
    }


}