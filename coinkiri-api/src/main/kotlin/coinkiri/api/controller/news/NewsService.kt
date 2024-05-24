package coinkiri.api.controller.news

import coinkiri.api.controller.news.dto.NewsResponseDto
import coinkiri.core.domain.news.NewsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NewsService (
    private val newsRepository: NewsRepository
){

    @Transactional(readOnly = true)
    fun findAllNews(): List<NewsResponseDto>{
        return newsRepository.findAll().map {
            NewsResponseDto(
                id = it.id,
                title = it.title,
                link = it.link,
                description = it.description,
                pubDate = it.pubDate
            )
        }
    }
}