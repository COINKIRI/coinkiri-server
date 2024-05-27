package coinkiri.api.controller.member.dto.response


data class MemberDetailResponseDto(
    val id: Long,
    val nickname: String,
    val exp: Int,
    val level: Int,
    val mileage: Int,
    val pic: ByteArray,
    val statusMessage: String,
    val followingCount: Int,
    val followerCount: Int,
)