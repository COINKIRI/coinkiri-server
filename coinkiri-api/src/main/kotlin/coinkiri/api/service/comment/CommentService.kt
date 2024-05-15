package coinkiri.api.service.comment

import coinkiri.api.controller.comment.dto.request.CommentRequestDto
import coinkiri.api.controller.comment.dto.response.CommentResponseDto
import coinkiri.core.domain.comment.Comment
import coinkiri.core.domain.comment.repository.CommentRepository
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.repository.CommunityRepository
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
    fun saveComment(request: CommentRequestDto) {
        val member = memberRepository.findById(request.memberId).get()
        val community = communityRepository.findById(request.postId).get()
        var parentComment: Comment? = null
        if (request.parentCommentId != 0L) { // 부모 댓글이 존재할 경우
            parentComment = commentRepository.findById(request.parentCommentId).get()
        }

        commentRepository.save(
            Comment(
                community,
                member,
                parentComment,
                request.content
            )
        )
    }

    // 댓글 조회
//    @Transactional(readOnly = true)
//    fun findComment(postId: Long): List<CommentResponseDto> {
//        val comments: List<Comment> = commentRepository.findByPostId(postId)
//
//        return comments.map {
//            CommentResponseDto(
//
//            )
//        }
//
//    }
}