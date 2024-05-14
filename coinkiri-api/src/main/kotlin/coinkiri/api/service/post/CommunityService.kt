package coinkiri.api.service.post

import coinkiri.api.controller.member.dto.response.MemberInfoDto
import coinkiri.api.controller.post.dto.request.CommunityRequestDto
import coinkiri.api.controller.post.dto.response.CommunityResponseDto
import coinkiri.api.controller.post.dto.response.PostResponseDto
import coinkiri.common.KotlinLogging.log
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.Community
import coinkiri.core.domain.post.Post
import coinkiri.core.domain.post.repository.CommunityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityService (
    private val communityRepository: CommunityRepository,
    private val memberRepository: MemberRepository,
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

    /**
     * 두 번의 쿼리 실행
     * -> fetch join 시 한 번으로 줄일 수 있긴함
     */
    @Transactional(readOnly = true)
    fun findCommunityPost(postId: Long): CommunityResponseDto {
        val community = communityRepository.findById(postId).get()
        community.increaseViewCnt() // 조회수 증가
        val member = community.member

        return CommunityResponseDto(
            PostResponseDto(
                community.id,
                community.title,
                community.content,
                community.viewCnt,
                community.createdAt,
                community.modifiedAt,
                MemberInfoDto(
                    member.nickname,
                    member.exp,
                    member.level,
                    member.mileage,
                    member.pic ?: ByteArray(0)
                )
            ),
            community.category
        )
    }

}