package coinkiri.api.controller.post.dto.response

import coinkiri.api.controller.comment.dto.response.CommentResponseDto
import coinkiri.api.controller.post.dto.request.ImageDto

data class PostResponseDto (
    val id: Long,
    val title: String,
    val viewCnt: Int,
    val createdAt: String,
    val memberNickname: String,
    val memberLevel: Int,
    val commentCount: Int,
    val likeCount: Int
)

data class PostDetailResponseDto (
    val title: String,
    val content: String,
    val viewCnt: Int,
    val createdAt: String,
    val memberNickname: String,
    val memberLevel: Int,
    val memberPic: ByteArray,
    val likeCount: Int,
    val images: List<ImageDto>,
    val comments: List<CommentResponseDto>
)

data class CommunityResponseDto (
    val postResponseDto: PostResponseDto,
    val category: String
)

data class CommunityDetailResponseDto (
    val postDetailResponseDto: PostDetailResponseDto,
    val category: String
)


