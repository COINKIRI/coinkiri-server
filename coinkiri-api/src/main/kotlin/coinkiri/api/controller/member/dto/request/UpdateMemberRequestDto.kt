package coinkiri.api.controller.member.dto.request


data class UpdateMemberRequestDto (
    val nickname: String,
    val pic: String,
    val statusMessage: String
)