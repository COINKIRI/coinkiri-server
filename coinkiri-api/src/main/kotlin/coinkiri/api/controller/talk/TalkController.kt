package coinkiri.api.controller.talk

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.config.resolver.MemberID
import coinkiri.api.service.talk.TalkService
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Talk")
@RestController
@RequestMapping("/api/v1/talk")
class TalkController (
    private val talkService: TalkService
){
    @Operation(summary = "[인증] 톡 작성")
    @PostMapping("/save")
    @Auth
    fun saveTalk(
        @MemberID memberId: Long,
        @RequestBody request: TalkRequestDto
    ) : ResponseEntity<ApiResponse<Any>> {
        talkService.saveTalk(memberId, request)
        return ResponseEntity.ok(ApiResponse.success())
    }
}