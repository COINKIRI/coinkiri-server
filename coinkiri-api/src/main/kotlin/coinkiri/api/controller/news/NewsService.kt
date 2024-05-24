package coinkiri.api.controller.news

import coinkiri.api.controller.news.dto.NewsResponseDto
import coinkiri.core.domain.news.News
import coinkiri.core.domain.news.NewsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NewsService (
    private val newsRepository: NewsRepository,
    private val newsApiCaller: NewsApiCaller
){

    @Transactional
    fun saveNews() {
        val newsList = newsApiCaller.getNews()
        newsRepository.deleteAll()
        val newsEntities = newsList.map {
            News(
                it.title,
                it.link,
                it.description,
                it.pubDate
            )
        }
        newsRepository.saveAll(newsEntities)
    }

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