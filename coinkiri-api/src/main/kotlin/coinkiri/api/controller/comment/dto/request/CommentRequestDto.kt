package coinkiri.api.controller.comment.dto.request

data class CommentRequestDto (
    val postId: Long,
    val memberId: Long,
    val parentCommentId: Long, // 답글이 아닐 경우 0을 받음
    val content: String
)