package coinkiri.api.service.comment

import coinkiri.api.controller.comment.dto.request.CommentRequestDto
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


}