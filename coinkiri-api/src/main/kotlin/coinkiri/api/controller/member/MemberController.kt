package coinkiri.api.controller.member

import coinkiri.api.controller.member.dto.request.RegisterDto
import coinkiri.api.controller.member.dto.request.UpdateNicknameDto
import coinkiri.api.service.MemberService
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Member")
@RestController
@RequestMapping("/api/v1/member")
class MemberController (
    private val memberService: MemberService
){

    // 회원 정보 저장 API
    @Operation(summary = "회원 정보 저장")
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterDto): ResponseEntity<ApiResponse<Any>>{
        memberService.saveMember(request)
        log.info { "회원 정보 저장 완료" }
        return ResponseEntity.ok(ApiResponse.success())
    }

    // 회원 확인 API
    @Operation(summary = "회원 확인")
    @GetMapping("/check/{socialId}")
    fun checkMember(@PathVariable socialId: String): ResponseEntity<ApiResponse<Any>> {
        val isMember = memberService.checkMember(socialId)
        return ResponseEntity.ok(ApiResponse.success(isMember))
    }


    // 닉네임 수정 API PathVariable
    @Operation(summary = "닉네임 수정")
    @PutMapping("/{socialId}/nickname")
    fun updateNickname(
        @PathVariable socialId: String,
        @RequestBody request: UpdateNicknameDto
    ): ResponseEntity<ApiResponse<Any>> {
        memberService.updateNickname(socialId, request.newNickname)
        return ResponseEntity.ok(ApiResponse.success())
    }

    // 마이페이지 (회원 정보 조회) API
    @Operation(summary = "마이페이지 조회")
    @GetMapping("/{socialId}")
    fun findMemberInfo(@PathVariable socialId: String): ResponseEntity<ApiResponse<Any>> {
        val memberInfo = memberService.findMemberInfo(socialId)
        return ResponseEntity.ok(ApiResponse.success(memberInfo))
    }

}