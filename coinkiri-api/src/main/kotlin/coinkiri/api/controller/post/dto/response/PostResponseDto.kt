package coinkiri.api.controller.post.dto.response

import coinkiri.api.controller.comment.dto.response.CommentResponseDto
import coinkiri.api.controller.member.dto.response.MemberInfoDto
import coinkiri.api.controller.post.dto.request.ImageDto
import java.time.LocalDateTime

data class PostResponseDto (
    val id: Long,
    val title: String,
    val viewCnt: Int,
    val memberNickname: String,
    val memberLevel: Int,
    val commentCount: Int,
    val likeCount: Int
)

data class CommunityResponseDto (
    val postResponseDto: PostResponseDto,
    val category: String
)

data class CommunityDetailResponseDto (
    val communityResponseDto: CommunityResponseDto,
    val comments: List<CommentResponseDto>
)

