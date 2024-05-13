package coinkiri.api.service.post

import coinkiri.api.controller.post.dto.request.CommunityRequestDto
import coinkiri.core.domain.post.repository.CommunityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityService (
    private val communityRepository: CommunityRepository
){
    @Transactional
    fun saveCommunityPost(request: CommunityRequestDto) {
        communityRepository.save(request.toEntity())
    }
}