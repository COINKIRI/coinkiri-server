package coinkiri.api.controller.member.dto.response


data class MemberInfoDto(
    val nickname: String,
    val exp: Int,
    val level: Int,
    val mileage: Int,
    val pic: ByteArray,
)
