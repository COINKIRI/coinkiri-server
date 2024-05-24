package coinkiri.api.controller.news.dto


data class NewsRequestDto (
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String,
)