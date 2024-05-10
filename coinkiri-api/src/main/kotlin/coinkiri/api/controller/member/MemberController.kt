package coinkiri.api.controller.member

import coinkiri.api.controller.member.dto.request.RegisterDto
import coinkiri.api.controller.member.dto.request.UpdateNicknameDto
import coinkiri.api.service.MemberService
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
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
        return ResponseEntity.ok(ApiResponse.success())
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

}