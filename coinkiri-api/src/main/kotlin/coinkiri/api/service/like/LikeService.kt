package coinkiri.api.service.like

import coinkiri.common.KotlinLogging.log
import coinkiri.core.domain.like.Like
import coinkiri.core.domain.like.LikeRepository
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.Post
import coinkiri.core.domain.post.repository.post.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LikeService (
    private val likeRepository: LikeRepository,
    private val memberRepository: MemberRepository,
    private val postRepository: PostRepository<Post>
){
    // 좋아요 추가 로직
    @Transactional
    fun saveLike(memberId: Long, postId: Long) {
        val member = memberRepository.findById(memberId).get()
        val post = postRepository.findPostById(postId) /* TODO 슈퍼타입만 조회할 수 있나? */

        if (likeRepository.existsByMemberAndPost(member, post)) {
            log.info { "이미 좋아요가 등록된 게시글입니다. memberId: $memberId, postId: $postId" }
        } else {
            log.info { "좋아요를 추가합니다. memberId: $memberId, postId: $postId" }
            likeRepository.save(Like(member, post))
        }
    }

    // 좋아요 삭제 로직
    @Transactional
    fun deleteLike(memberId: Long, postId: Long) {
        val member = memberRepository.findById(memberId).get()
        val post = postRepository.findPostById(postId)

        if (!likeRepository.existsByMemberAndPost(member, post)) {
            log.info { "좋아요가 등록되어있지 않은 게시글입니다. memberId: $memberId, postId: $postId" }
            return
        } else {
            log.info { "좋아요를 삭제합니다. memberId: $memberId, postId: $postId" }
            likeRepository.deleteByMemberAndPost(member, post)
        }
    }

    // 좋아요 여부 확인 로직
    @Transactional(readOnly = true)
    fun checkLike(memberId: Long, postId: Long): Boolean {
        val member = memberRepository.findById(memberId).get()
        val post = postRepository.findPostById(postId)

        return likeRepository.existsByMemberAndPost(member, post)
    }
}