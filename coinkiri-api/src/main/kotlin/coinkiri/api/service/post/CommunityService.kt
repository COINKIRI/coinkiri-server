package coinkiri.api.service.post

import coinkiri.api.controller.comment.dto.response.CommentResponseDto
import coinkiri.api.controller.member.dto.response.MemberResponseDto
import coinkiri.api.controller.post.dto.request.ImageDto
import coinkiri.api.controller.post.dto.request.PostRequestDto
import coinkiri.api.controller.post.dto.response.CommunityDetailResponseDto
import coinkiri.api.controller.post.dto.response.CommunityResponseDto
import coinkiri.api.controller.post.dto.response.PostDetailResponseDto
import coinkiri.api.controller.post.dto.response.PostResponseDto
import coinkiri.core.domain.image.Image
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.community.Community
import coinkiri.core.domain.post.community.repository.CommunityRepository
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

    // 커뮤니티 전체 조회 - refactored
    @Transactional(readOnly = true)
    fun findAllCommunity(): List<CommunityResponseDto> {
        return communityRepository.findAllWithMemberAndCommentAndLike().map {
            CommunityResponseDto(
                PostResponseDto(
                    it.id,
                    it.title,
                    it.viewCnt,
                    it.createdAt.toString(),
                    it.member.nickname, // member many-to-one
                    it.member.level,
                    it.comments.size, // comment one-to-many
                    it.likes.size // like one-to-many
                ),
                it.category.toString()
            )
        }
    }

    // 커뮤니티 글 상세 조회
    @Transactional(readOnly = true)
    fun findCommunityDetail(postId: Long): CommunityDetailResponseDto {
        val community = communityRepository.findOneWithMemberAndCommentAndLike(postId)
        return CommunityDetailResponseDto(
            PostDetailResponseDto(
                community.title,
                community.content,
                community.viewCnt,
                community.createdAt.toString(),
                community.member.nickname,
                community.member.level,
                community.member.pic,
                community.likes.size,
                community.images.map {
                    ImageDto(
                        it.position,
                        Base64.encodeBase64String(it.image)
                    )
                },
                community.comments.map {
                    CommentResponseDto(
                        it.id,
                        it.content,
                        it.createdAt.toString(),
                        it.modifiedAt.toString(),
                        MemberResponseDto(
                            it.member.id,
                            it.member.nickname,
                            it.member.level,
                            it.member.pic
                        )
                    )
                }
            ),
            community.category.toString()
        )
    }


}