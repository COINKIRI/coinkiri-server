package coinkiri.api.service.comment

import coinkiri.api.controller.comment.dto.request.CommentRequestDto
import coinkiri.api.controller.comment.dto.response.CommentResponseDto
import coinkiri.api.controller.member.dto.response.MemberInfoDto
import coinkiri.core.domain.comment.Comment
import coinkiri.core.domain.comment.repository.CommentRepository
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.repository.community.CommunityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService (
    private val commentRepository: CommentRepository,
    private val memberRepository: MemberRepository,
    private val communityRepository: CommunityRepository
){

    // 댓글 작성
    @Transactional
    fun saveComment(memberId: Long, request: CommentRequestDto) {
        val member = memberRepository.findById(memberId).get()
        val community = communityRepository.findById(request.postId).get()

        commentRepository.save(
            Comment(
                community,
                member,
                request.content
            )
        )
    }

    // 댓글 조회
    /**
     * join하지 않을 시 댓글 개수*2 번의 쿼리 추가 발생
     */
    @Transactional(readOnly = true)
    fun findComment(postId: Long): List<CommentResponseDto> {
        return commentRepository.findWithMemberByPostId(postId).map { // byPostId -> post는 자동으로 join
            CommentResponseDto(
                it.id,
                it.content,
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
            )
        }
    }


}