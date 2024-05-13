package coinkiri.api.controller.post.dto.request

import coinkiri.core.domain.member.Member
import coinkiri.core.domain.post.Community


data class PostRequestDto (
    val title: String,
    val content: String,
    val member: Member, // MemberDto 를 넣어야함
)

data class CommunityRequestDto (
    val postRequestDto: PostRequestDto,
    val category: String
) {
    fun toEntity(): Community {
        return Community(
            postRequestDto.title,
            postRequestDto.content,
            postRequestDto.member,
            category
        )
    }
}

data class MissionRequestDto (
    val postRequestDto: PostRequestDto,
    val coinId: Long,
    val endTime: String
)

data class AnalysisRequestDto (
    val postRequestDto: PostRequestDto,
    val coinId: Long,
    val opinion: String,
    val targetPeriod: String,
    val targetPrice: String
)