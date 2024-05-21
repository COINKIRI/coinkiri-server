package coinkiri.api.service.follow

import coinkiri.core.domain.follow.Follow
import coinkiri.core.domain.follow.repository.FollowRepository
import coinkiri.core.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FollowService (
    private val followRepository: FollowRepository,
    private val memberRepository: MemberRepository
){
    @Transactional
    fun saveFollow(memberId: Long, followId: Long) {
        val member = memberRepository.findById(memberId).get()
        val followMember = memberRepository.findById(followId).get()

        followRepository.save(
            Follow(
                member, followMember
            )
        )
    }
}