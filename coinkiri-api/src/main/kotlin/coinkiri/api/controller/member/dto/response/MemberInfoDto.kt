package coinkiri.api.controller.member.dto.response


data class MemberInfoDto(
    val id: Long,
    val nickname: String,
    val exp: Int,
    val level: Int,
    val mileage: Int,
    val pic: ByteArray,
    val statusMessage: String,
)
