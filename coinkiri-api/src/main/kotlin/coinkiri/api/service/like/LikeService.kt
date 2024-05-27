package coinkiri.api.service.like

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
    // 좋아요 추가, 삭제 로직
    @Transactional
    fun updateLike(memberId: Long, postId: Long) {
        val member = memberRepository.findById(memberId).get()
        val post = postRepository.findPostById(postId) /* TODO 슈퍼타입만 조회할 수 있나? */

        // 있으면 삭제, 없으면 저장
        if (likeRepository.existsByMemberAndPost(member, post)) {
            likeRepository.deleteByMemberAndPost(member, post)
        } else {
            likeRepository.save(Like(member, post))
        }
    }
}