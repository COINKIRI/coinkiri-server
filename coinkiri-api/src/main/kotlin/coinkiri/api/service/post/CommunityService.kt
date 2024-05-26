package coinkiri.api.service.post

import coinkiri.api.config.resolver.MemberID
import coinkiri.api.controller.comment.dto.response.CommentResponseDto
import coinkiri.api.controller.member.dto.response.MemberInfoDto
import coinkiri.api.controller.post.dto.request.CommunityRequestDto
import coinkiri.api.controller.post.dto.request.ImageDto
import coinkiri.api.controller.post.dto.request.PostRequestDto
import coinkiri.api.controller.post.dto.response.CommunityResponseDto
import coinkiri.api.controller.post.dto.response.PostResponseDto
import coinkiri.common.KotlinLogging.log
import coinkiri.core.domain.image.Image
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.Community
import coinkiri.core.domain.post.Post
import coinkiri.core.domain.post.repository.community.CommunityRepository
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityService (
    private val communityRepository: CommunityRepository,
    private val memberRepository: MemberRepository,
){

    // 커뮤니티 글 작성 - 작성자 + 커뮤니티 글 + 이미지 저장
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
     * 추가 쿼리 발생
     * -> querydsl로 해결 -> post+community+image(커뮤니티 글+이미지) + member(작성자) + comment(댓글) 한번에 조회
     */
    // 커뮤니티 전체 조회 + 상세 조회
    @Transactional(readOnly = true)
    fun findAllCommunity(): List<CommunityResponseDto> {
        return communityRepository.findAllWithMemberAndImageAndComment().map {
            CommunityResponseDto(
                PostResponseDto(
                    it.id,
                    it.title,
                    it.content,
                    it.images.map { image -> ImageDto(
                        image.position,
                        Base64.encodeBase64String(image.image)
                    ) },
                    it.viewCnt,
                    it.createdAt,
                    it.modifiedAt,
                    MemberInfoDto(
                        it.member.id,
                        it.member.nickname,
                        it.member.exp,
                        it.member.level,
                        it.member.mileage,
                        it.member.pic,
                        it.member.statusMessage
                    ),
                    it.comments.map { comment -> CommentResponseDto(
                        comment.id,
                        comment.content,
                        comment.createdAt,
                        comment.modifiedAt,
                        MemberInfoDto(
                            comment.member.id,
                            comment.member.nickname,
                            comment.member.exp,
                            comment.member.level,
                            comment.member.mileage,
                            comment.member.pic,
                            comment.member.statusMessage
                            )
                    )}
                ),
                it.category.toString()
            )
        }
    }

}