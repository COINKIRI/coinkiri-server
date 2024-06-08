package coinkiri.api.service.like

import coinkiri.api.controller.coin.dto.response.CoinResponseDto
import coinkiri.api.controller.post.dto.response.AnalysisResponseDto
import coinkiri.api.controller.post.dto.response.CommunityResponseDto
import coinkiri.api.controller.post.dto.response.PostResponseDto
import coinkiri.common.KotlinLogging.log
import coinkiri.core.domain.like.Like
import coinkiri.core.domain.like.LikeRepository
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.Post
import coinkiri.core.domain.post.analysis.Analysis
import coinkiri.core.domain.post.analysis.repository.AnalysisRepository
import coinkiri.core.domain.post.community.Community
import coinkiri.core.domain.post.community.repository.CommunityRepository
import coinkiri.core.domain.post.repository.post.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LikeService (
    private val likeRepository: LikeRepository,
    private val memberRepository: MemberRepository,
    private val postRepository: PostRepository<Post>,
    private val communityRepository: CommunityRepository,
    private val analysisRepository: AnalysisRepository
) {
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


    // 좋아요한 커뮤니티 글 조회 로직
    @Transactional(readOnly = true)
    fun findLikeCommunity(memberId: Long): List<CommunityResponseDto> {
        val member = memberRepository.findById(memberId).get()
        val likeList = likeRepository.findByMember(member)
        return communityRepository.findAllById(likeList.map { it.post.id }).map {
            CommunityResponseDto(
                PostResponseDto(
                    it.id,
                    it.title,
                    it.viewCnt,
                    it.createdAt.toString(),
                    it.member.nickname,
                    it.member.level,
                    it.comments.size,
                    it.likes.size
                ),
                it.category.toString()
            )
        }
    }

    // 좋아요한 분석 글 조회 로직
    @Transactional(readOnly = true)
    fun findLikeAnalysis(memberId: Long): List<AnalysisResponseDto> {
        val member = memberRepository.findById(memberId).get()
        val likeList = likeRepository.findByMember(member)
        return analysisRepository.findAllById(likeList.map { it.post.id }).map {
            AnalysisResponseDto(
                PostResponseDto(
                    it.id,
                    it.title,
                    it.viewCnt,
                    it.createdAt.toString(),
                    it.member.nickname,
                    it.member.level,
                    it.comments.size,
                    it.likes.size
                ),
                CoinResponseDto(
                    it.coin.coinId,
                    it.coin.market,
                    it.coin.koreanName,
                    it.coin.englishName,
                    it.coin.symbolImage
                ),
                it.member.pic,
                it.coinPrevClosingPrice,
                it.investmentOption.value,
                it.targetPrice,
                it.targetPeriod
            )
        }
    }
}
