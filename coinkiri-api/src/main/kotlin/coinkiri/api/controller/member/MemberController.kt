package coinkiri.api.controller.member

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.config.resolver.MemberID
import coinkiri.api.controller.member.dto.request.UpdateMemberRequestDto
import coinkiri.api.controller.member.dto.request.UpdatePicRequestDto
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

    // 마이페이지 (회원 정보 조회) API - 토큰으로 조회
    @Auth
    @Operation(summary = "[인증] 마이페이지 조회")
    @GetMapping("/info")
    fun findMemberInfo(@MemberID memberId: Long): ResponseEntity<ApiResponse<MemberDetailResponseDto>> {
        val memberInfo = memberService.findMemberInfo(memberId)
        return ResponseEntity.ok(ApiResponse.success(memberInfo))
    }

    // 다른 회원 정보 조회 API - memberId로 조회
    @Operation(summary = "다른 회원 정보 조회")
    @GetMapping("/{memberId}")
    fun findOtherMemberInfo(@PathVariable memberId: Long): ResponseEntity<ApiResponse<MemberDetailResponseDto>> {
        val memberInfo = memberService.findMemberInfo(memberId)
        return ResponseEntity.ok(ApiResponse.success(memberInfo))
    }

    // 유저 정보(닉네임, 상태 메세지) 수정 API
    @Auth
    @Operation(summary = "[인증] 유저 정보 수정")
    @PutMapping("/update")
    fun updateMemberInfo(
        @MemberID memberId: Long,
        @RequestBody request: UpdateMemberRequestDto
    ) : ResponseEntity<ApiResponse<Any>>{
        val memberInfo = memberService.updateMemberInfo(memberId, request)
        log.info { "유저 정보 수정 완료. memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success(memberInfo))
    }

    // 프로필 사진 수정 API
    @Auth
    @Operation(summary = "[인증] 프로필 사진 수정")
    @PutMapping("/update/pic")
    fun updateProfilePic(
        @MemberID memberId: Long,
        @RequestBody pic: UpdatePicRequestDto
    ): ResponseEntity<ApiResponse<Any>> {
        val memberInfo = memberService.updateProfilePic(memberId, pic)
        log.info { "프로필 사진 수정 완료. memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success(memberInfo))
    }

    // 회원 탈퇴 API
    @Auth
    @Operation(summary = "[인증] 회원 탈퇴")
    @DeleteMapping("/withdraw")
    fun withdraw(@MemberID memberId: Long): ResponseEntity<ApiResponse<Any>> {
        memberService.withdraw(memberId)
        log.info { "회원 탈퇴 완료. memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success())
    }





}