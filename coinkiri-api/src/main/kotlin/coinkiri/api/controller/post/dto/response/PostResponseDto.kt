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
    val coinPrevClosingPrice: String,
    val investmentOption: String,
    val targetPrice: String,
    val targetPeriod: String
)

data class AnalysisDetailResponseDto (
    val postDetailResponseDto: PostDetailResponseDto,
    val coin: CoinResponseDto,
    val coinPrevClosingPrice: String,
    val investmentOption: String,
    val targetPrice: String,
    val targetPeriod: String,
)

data class CommunityDetailResponseDto (
    val postDetailResponseDto: PostDetailResponseDto,
    val category: String
)

data class PostDetailResponseDto (
    val id: Long,
    val title: String,
    val content: String,
    val viewCnt: Int,
    val createdAt: String,
    val memberNickname: String,
    val memberLevel: Int,
    val memberPic: ByteArray,
    val likeCount: Int,
    val images: List<ImageDto>,
    val commentCount: Int
)




