package coinkiri.api.controller.comment.dto.request

data class CommentRequestDto (
    val postId: Long,
    val content: String
)