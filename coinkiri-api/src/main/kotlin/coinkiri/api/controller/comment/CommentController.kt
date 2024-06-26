package coinkiri.api.controller.comment

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.config.resolver.MemberID
import coinkiri.api.controller.comment.dto.request.CommentRequestDto
import coinkiri.api.controller.comment.dto.response.CommentResponseDto
import coinkiri.api.service.comment.CommentService
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Comment")
@RestController
@RequestMapping("/api/v1/comment")
class CommentController(
    private val commentService: CommentService
) {

    // 댓글 작성 API
    @Operation(summary = "[인증] 댓글 작성")
    @PostMapping("/save")
    @Auth
    fun saveComment(
        @MemberID memberId: Long,
        @RequestBody request: CommentRequestDto
    ): ResponseEntity<ApiResponse<Any>>{
        commentService.saveComment(memberId, request)
        log.info { "댓글 작성 완료. memberId: $memberId" }
        return ResponseEntity.ok(ApiResponse.success())
    }

    // 댓글 조회 API
    @Operation(summary = "댓글 조회")
    @GetMapping("/{postId}")
    fun getCommentList(
        @PathVariable postId: Long
    ): ResponseEntity<ApiResponse<List<CommentResponseDto>>>{
        val commentList = commentService.findCommentList(postId)
        log.info { "댓글 조회 완료. postId: $postId" }
        return ResponseEntity.ok(ApiResponse.success(commentList))
    }

}