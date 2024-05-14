package coinkiri.api.controller.post.dto.request


data class PostRequestDto ( // 슈퍼타입 dto
    val title: String,
    val content: String,
    val memberId: Long, // id만 받고 member 객체로 변환하는 로직 추가
)

data class CommunityRequestDto ( // 서브타입 dto
    val postRequestDto: PostRequestDto,
    val category: String
)

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