package coinkiri.api.controller.post.dto.response

import coinkiri.api.controller.comment.dto.response.CommentResponseDto
import coinkiri.api.controller.member.dto.response.MemberInfoDto
import coinkiri.api.controller.post.dto.request.ImageDto
import java.time.LocalDateTime

data class PostResponseDto (
    val id: Long,
    val title: String,
    val content: String,
    val images: List<ImageDto>,
    val viewCnt: Int,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
    val member: MemberInfoDto,
    val comments: List<CommentResponseDto>
)

data class CommunityResponseDto (
    val postResponseDto: PostResponseDto,
    val category: String
)

data class CommunityDetailResponseDto (
    val communityResponseDto: CommunityResponseDto,
    val comments: List<CommentResponseDto>
)