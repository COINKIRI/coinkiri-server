package coinkiri.api.service.post

import coinkiri.api.controller.post.dto.request.CommunityRequestDto
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.Community
import coinkiri.core.domain.post.repository.CommunityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityService (
    private val communityRepository: CommunityRepository,
    private val memberRepository: MemberRepository
){
    @Transactional
    fun saveCommunityPost(request: CommunityRequestDto) {
        val member = memberRepository.findById(request.postRequestDto.memberId).get()

        communityRepository.save(
            Community.builder()
                .title(request.postRequestDto.title)
                .content(request.postRequestDto.content)
                .member(member)
                .category(request.category)
                .build()
        )
    }
}