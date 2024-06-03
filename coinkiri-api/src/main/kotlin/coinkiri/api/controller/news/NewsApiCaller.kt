package coinkiri.api.controller.news

import coinkiri.api.controller.news.dto.NewsRequestDto
import coinkiri.common.KotlinLogging.log
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.nio.charset.StandardCharsets

@Component
class NewsApiCaller (
    private val restTemplate: RestTemplate
) {
    @Value("\${naver.client-id}")
    private val clientId: String? = null

    @Value("\${naver.client-secret}")
    private val clientSecret: String? = null

    fun getNews(): List<NewsRequestDto> {
        log.info { "clientId: $clientId" }
        log.info { "clientSecret: $clientSecret" }

        val uri: URI = UriComponentsBuilder
            .fromUriString("https://openapi.naver.com/v1/search/news.json")
            .queryParam("query", "암호화폐")
            .queryParam("display", 50)
            .queryParam("start", 1)
            .queryParam("sort", "date")
            .encode(StandardCharsets.UTF_8)
            .build()
            .toUri()

        val headers = HttpHeaders().apply {
            set("X-Naver-Client-Id", clientId ?: throw IllegalArgumentException("clientId is missing"))
            set("X-Naver-Client-Secret", clientSecret ?: throw IllegalArgumentException("clientSecret is missing"))
        }

        val req = RequestEntity
            .get(uri)
            .headers(headers)
            .build()

        val response = restTemplate.exchange(req, NewsResponse::class.java)

        return response.body?.items?.map {
            NewsRequestDto(
                title = it.title,
                link = it.link,
                description = removeHtmlTags(it.description),
                pubDate = it.pubDate
            )
        } ?: throw RuntimeException("News API 응답이 null입니다.")

    }

    private fun removeHtmlTags(input: String): String {
        return input.replace("<b>", "").replace("</b>", "")
    }


    data class NewsResponse(
        val lastBuildDate: String,
        val total: Int,
        val start: Int,
        val display: Int,
        val items: List<NewsItem>
    )

    data class NewsItem(
        val title: String,
        val originallink: String,
        val link: String,
        val description: String,
        val pubDate: String
    )
}
