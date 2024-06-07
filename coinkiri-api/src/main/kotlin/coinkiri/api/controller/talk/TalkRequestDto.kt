package coinkiri.api.controller.talk


data class TalkRequestDto (
    val content: String,
    val coinId: Long
)