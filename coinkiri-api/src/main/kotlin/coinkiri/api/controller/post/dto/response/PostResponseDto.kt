package coinkiri.api.controller.post.dto.response

import coinkiri.api.controller.coin.dto.response.CoinResponseDto
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

data class CommunityResponseDto (
    val postResponseDto: PostResponseDto,
    val category: String
)

data class AnalysisResponseDto (
    val postResponseDto: PostResponseDto,
    val coin: CoinResponseDto,
    val opinion: String,
    val targetPeriod: Int,
    val targetPrice: Double
)

data class CommunityDetailResponseDto (
    val postDetailResponseDto: PostDetailResponseDto,
    val category: String
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




