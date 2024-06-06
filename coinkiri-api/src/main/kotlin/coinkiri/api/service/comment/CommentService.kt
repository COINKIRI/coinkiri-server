package coinkiri.api.service.comment

import coinkiri.api.controller.comment.dto.request.CommentRequestDto
import coinkiri.api.controller.comment.dto.response.CommentResponseDto
import coinkiri.api.controller.member.dto.response.MemberResponseDto
import coinkiri.core.domain.comment.Comment
import coinkiri.core.domain.comment.repository.CommentRepository
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.Post
import coinkiri.core.domain.post.community.repository.CommunityRepository
import coinkiri.core.domain.post.repository.post.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService (
    private val commentRepository: CommentRepository,
    private val memberRepository: MemberRepository,
    private val postRepository: PostRepository<Post>
){

    // 댓글 작성
    @Transactional
    fun saveComment(memberId: Long, request: CommentRequestDto) {
        val member = memberRepository.findById(memberId).get()
        val post = postRepository.findById(request.postId).get()

        commentRepository.save(
            Comment(
                post,
                member,
                request.content
            )
        )
    }

    // 댓글 조회
    @Transactional(readOnly = true)
    fun findCommentList(postId: Long): List<CommentResponseDto> {
        return commentRepository.findAllByPostId(postId).map {
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
    }


}