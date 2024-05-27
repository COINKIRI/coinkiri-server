package coinkiri.api.controller.member.dto.response


data class MemberResponseDto(
    val id: Long,
    val nickname: String,
    val level: Int,
    val pic: ByteArray,
)
