package coinkiri.api.controller.news.dto

import java.time.LocalDateTime


data class NewsResponseDto (
    val id: Long,
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String,
)