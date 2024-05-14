package coinkiri.api.service.post

import coinkiri.api.controller.post.dto.request.CommunityRequestDto
import coinkiri.api.controller.post.dto.response.CommunityResponseDto
import coinkiri.api.controller.post.dto.response.PostResponseDto
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.Community
import coinkiri.core.domain.post.repository.CommunityRepository
import coinkiri.core.domain.post.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityService (
    private val communityRepository: CommunityRepository,
    private val memberRepository: MemberRepository,
    private val postRepository: PostRepository<Community>
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

    @Transactional(readOnly = true)
    fun findCommunityPost(postId: Long): Community {
        return postRepository.findById(postId).get()
    }

}