package coinkiri.api.controller.news

import coinkiri.common.KotlinLogging.log
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.HttpClientErrorException
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

    fun getNews(): String {
        log.info { "clientId: $clientId" }
        log.info { "clientSecret: $clientSecret" }

        val uri: URI = UriComponentsBuilder
            .fromUriString("https://openapi.naver.com/v1/search/news.json")
            .queryParam("query", "암호화폐")
            .queryParam("display", 10)
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

        return try {
            val result = restTemplate.exchange(req, String::class.java)
            log.info { result.body }
            result.body ?: ""
        } catch (ex: HttpClientErrorException) {
            log.error { "Error during API call: ${ex.statusCode} ${ex.statusText}: ${ex.responseBodyAsString}" }
            throw ex
        }
    }
}
