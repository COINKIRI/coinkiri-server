package coinkiri.api.controller.post.dto.request


data class PostRequestDto ( // 슈퍼타입 dto
    val title: String,
    val content: String,
    val images: List<ImageDto>
)

data class ImageDto ( // 이미지 데이터
    val position: Int,
    val base64: String
)

data class CommunityRequestDto ( // 서브타입 dto
    val postRequestDto: PostRequestDto,
//    val category: String -> TODO 현재 카테고리 기능이 없어서 백에서 기본값을 주면서 저장 중
)

data class MissionRequestDto (
    val postRequestDto: PostRequestDto,
    val coinId: Long,
    val endTime: String
)

data class AnalysisRequestDto (
    val postRequestDto: PostRequestDto,
    val coinId: Long,
    val currentPrice: Double,
    val opinion: String,
    val targetPeriod: Int,
    val expectedYield: Double,
    val targetPrice: Double
)