package coinkiri.api.controller.talk


data class TalkRequestDto (
    val title: String,
    val content: String,
    val coinId: Long
)