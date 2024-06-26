package coinkiri.api.controller.news

import coinkiri.api.controller.news.dto.NewsResponseDto
import coinkiri.common.KotlinLogging.log
import coinkiri.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "News")
@RestController
@RequestMapping("/api/v1/news")
class NewsController (
    private val newsService: NewsService,
){

    @Operation(summary = "[관리자] 뉴스 업데이트")
    @GetMapping("/test")
    fun saveNews(): ResponseEntity<ApiResponse<Any>> {
        newsService.saveNews()
        log.info { "뉴스를 업데이트하는 데 성공했습니다" }
        return ResponseEntity.ok(ApiResponse.success())
    }

    @Operation(summary = "뉴스 목록 조회")
    @GetMapping("/list")
    fun findAllNews(): ResponseEntity<ApiResponse<List<NewsResponseDto>>> {
        val newsList = newsService.findAllNews()
        log.info { "뉴스를 가져오는 데 성공했습니다" }
        return ResponseEntity.ok(ApiResponse.success(newsList))
    }

}