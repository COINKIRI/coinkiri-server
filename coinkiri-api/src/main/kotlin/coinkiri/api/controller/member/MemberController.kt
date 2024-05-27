package coinkiri.api.controller.member

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.config.resolver.MemberID
import coinkiri.api.controller.member.dto.request.UpdateNicknameRequestDto
import coinkiri.api.controller.member.dto.response.MemberDetailResponseDto
import coinkiri.api.service.member.MemberService
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Member")
@RestController
@RequestMapping("/api/v1/member")
class MemberController (
    private val memberService: MemberService
){

    // 마이페이지 (회원 정보 조회) API
    @Auth
    @Operation(summary = "[인증] 마이페이지 조회")
    @GetMapping("/info")
    fun findMemberInfo(@MemberID memberId: Long): ResponseEntity<ApiResponse<MemberDetailResponseDto>> {
        val memberInfo = memberService.findMemberInfo(memberId)
        return ResponseEntity.ok(ApiResponse.success(memberInfo))
    }

    // 회원 탈퇴 API
    @Auth
    @Operation(summary = "[인증] 회원 탈퇴")
    @GetMapping("/withdraw")
    fun withdraw(@MemberID memberId: Long): ResponseEntity<ApiResponse<Any>> {
        memberService.withdraw(memberId)
        log.info { "회원 탈퇴 완료. memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success())
    }

    // 닉네임 수정 API
    @Auth
    @Operation(summary = "[인증] 닉네임 수정")
    @PutMapping("/nickname")
    fun updateNickname(
        @MemberID memberId: Long,
        @RequestBody request: UpdateNicknameRequestDto
    ): ResponseEntity<ApiResponse<Any>> {
        memberService.updateNickname(memberId, request.newNickname)
        log.info { "닉네임 수정 완료. memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success())
    }

    // 상태 메시지 수정 API
    @Auth
    @Operation(summary = "[인증] 상태 메시지 수정")
    @PutMapping("/status")
    fun updateStatusMessage(
        @MemberID memberId: Long,
        request: UpdateNicknameRequestDto
    ): ResponseEntity<ApiResponse<Any>> {
        memberService.updateStatusMessage(memberId, request.newNickname)
        log.info { "상태 메시지 수정 완료. memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success())
    }



}