package coinkiri.api.controller.member

import coinkiri.api.controller.member.dto.request.RegisterDto
import coinkiri.api.service.MemberService
import coinkiri.common.response.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/member")
class MemberController (
    private val memberService: MemberService
){

    // 회원 정보 저장 API
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterDto): ResponseEntity<ApiResponse<Any>>{
        memberService.saveMember(request)
        return ResponseEntity.ok(ApiResponse.success())
    }
}