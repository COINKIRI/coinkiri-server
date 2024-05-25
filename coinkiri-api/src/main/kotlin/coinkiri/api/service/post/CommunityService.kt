package coinkiri.api.service.post

import coinkiri.api.config.resolver.MemberID
import coinkiri.api.controller.member.dto.response.MemberInfoDto
import coinkiri.api.controller.post.dto.request.CommunityRequestDto
import coinkiri.api.controller.post.dto.request.PostRequestDto
import coinkiri.api.controller.post.dto.response.CommunityResponseDto
import coinkiri.api.controller.post.dto.response.PostResponseDto
import coinkiri.common.KotlinLogging.log
import coinkiri.core.domain.image.Image
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.Community
import coinkiri.core.domain.post.Post
import coinkiri.core.domain.post.repository.community.CommunityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityService (
    private val communityRepository: CommunityRepository,
    private val memberRepository: MemberRepository,
){

    // 커뮤니티 글 작성
    @Transactional
    fun saveCommunityPost(memberId: Long, request: PostRequestDto) {

        // 작성자
        val member = memberRepository.findById(memberId).get()

        // 커뮤니티 글 생성
        val community = Community(
            request.title,
            request.content,
            member,
            "FREE"
        )

        // 이미지 추가 + 커뮤니티와 연관관계 설정
        request.images.forEach {
            val image = Image(
                it.position,
                it.base64.toByteArray(),
                community
            )
            community.addImage(image)
        }

        // 저장
        communityRepository.save(community)
    }

    /**
     * join하지 않을 시 글 개수x2번의 쿼리 추가 발생
     * -> querydsl로 해결 -> community, member join
     */
    // 커뮤니티 전체 조회 (상세 조회는 댓글 api 요청으로 구현)
    @Transactional(readOnly = true)
    fun findAllCommunityWithMember(): List<CommunityResponseDto> {
        return communityRepository.findAllWithMember().map {
            CommunityResponseDto(
                PostResponseDto(
                    it.id,
                    it.title,
                    it.content,
                    it.viewCnt,
                    it.createdAt,
                    it.modifiedAt,
                    MemberInfoDto(
                        it.member.id,
                        it.member.nickname,
                        it.member.exp,
                        it.member.level,
                        it.member.mileage,
                        it.member.pic ?: byteArrayOf(),
                        it.member.statusMessage
                    )
                ),
                it.category.toString()
            )
        }
    }

}