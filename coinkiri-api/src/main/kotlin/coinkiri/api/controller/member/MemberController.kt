package coinkiri.api.controller.member

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.config.resolver.MemberID
import coinkiri.api.controller.member.dto.response.MemberInfoDto
import coinkiri.api.service.member.MemberService
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Member")
@RestController
@RequestMapping("/api/v1/member")
class MemberController (
    private val memberService: MemberService
){

    // 마이페이지 (회원 정보 조회) API
    @Auth
    @Operation(summary = "마이페이지 조회")
    @GetMapping("/info")
    fun findMemberInfo(@MemberID memberId: Long): ResponseEntity<ApiResponse<MemberInfoDto>> {
        val memberInfo = memberService.findMemberInfo(memberId)
        return ResponseEntity.ok(ApiResponse.success(memberInfo))
    }

    // 회원 탈퇴 API
    @Auth
    @Operation(summary = "회원 탈퇴")
    @GetMapping("/withdraw")
    fun withdraw(@MemberID memberId: Long): ResponseEntity<ApiResponse<Any>> {
        memberService.withdraw(memberId)
        log.info { "회원 탈퇴 완료. memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success())
    }



}