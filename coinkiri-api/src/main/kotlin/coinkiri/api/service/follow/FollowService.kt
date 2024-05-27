package coinkiri.api.service.follow

import coinkiri.api.controller.member.dto.response.MemberResponseDto
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

    // 팔로우 저장, 삭제
    @Transactional
    fun updateFollow(memberId: Long, followId: Long) {
        val member = memberRepository.findById(memberId).get()
        val followMember = memberRepository.findById(followId).get()

        // 있으면 삭제, 없으면 저장
        if(followRepository.existsByFollowerAndFollowing(member, followMember)) {
            followRepository.deleteByFollowerAndFollowing(member, followMember)
        } else {
            followRepository.save(Follow(member, followMember))
        }
    }

    // 팔로잉 목록 조회
    @Transactional(readOnly = true)
    fun findFollowingList(memberId: Long): List<MemberResponseDto> {
        val followingList = followRepository.findWithFollowerAndFollowingByFollowerId(memberId)

        return followingList.map {
            MemberResponseDto(
                it.following.id,
                it.following.nickname,
                it.following.level,
                it.following.pic ?: ByteArray(0),
            )
        }
    }

    // 팔로워 목록 조회
    @Transactional(readOnly = true)
    fun findFollowerList(memberId: Long): List<MemberResponseDto> {
        val followerList = followRepository.findWithFollowerAndFollowingByFollowingId(memberId)

        return followerList.map {
            MemberResponseDto(
                it.following.id,
                it.following.nickname,
                it.following.level,
                it.following.pic ?: ByteArray(0),
            )
        }
    }
}