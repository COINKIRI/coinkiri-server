package coinkiri.api.service.post

import coinkiri.api.controller.member.dto.response.MemberInfoDto
import coinkiri.api.controller.post.dto.request.CommunityRequestDto
import coinkiri.api.controller.post.dto.response.CommunityResponseDto
import coinkiri.api.controller.post.dto.response.PostResponseDto
import coinkiri.core.domain.comment.repository.CommentRepository
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.Community
import coinkiri.core.domain.post.repository.community.CommunityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityService (
    private val communityRepository: CommunityRepository,
    private val memberRepository: MemberRepository,
    private val commentRepository: CommentRepository
){

    // 커뮤니티 글 작성
    @Transactional
    fun saveCommunityPost(memberId: Long, request: CommunityRequestDto) {
        val member = memberRepository.findById(memberId).get()

        communityRepository.save(
            Community.builder()
                .title(request.postRequestDto.title)
                .content(request.postRequestDto.content)
                .member(member)
                .category(request.category)
                .build()
        )
    }


    // 커뮤니티 상세 조회 - 댓글까지 조회
    @Transactional(readOnly = true)
    fun findCommunityPost(postId: Long): CommunityResponseDto {
        val community = communityRepository.findById(postId).get() // 커뮤니티 글 정보


    }


    /**
     * 현재는 네임드 쿼리 - * 2번 쿼리 실행
     * -> fetch join으로 줄여야 함
     */
    // 커뮤니티 전체 조회
    @Transactional(readOnly = true)
    fun findAllCommunityPost(): List<CommunityResponseDto> {
        return communityRepository.findAll().map {
            val member = it.member
            CommunityResponseDto(
                PostResponseDto(
                    it.id,
                    it.title,
                    it.content,
                    it.viewCnt,
                    it.createdAt,
                    it.modifiedAt,
                    MemberInfoDto(
                        member.nickname,
                        member.exp,
                        member.level,
                        member.mileage,
                        member.pic ?: ByteArray(0)
                    )
                ),
                it.category.toString()
            )
        }
    }

}