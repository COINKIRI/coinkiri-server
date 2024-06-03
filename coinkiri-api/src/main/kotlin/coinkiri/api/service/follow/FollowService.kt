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

    // 팔로우 저장
    @Transactional
    fun saveFollow(memberId: Long, followMemberId: Long) {
        val member = memberRepository.findById(memberId).orElseThrow { throw IllegalArgumentException("존재하지 않는 회원입니다.") }
        val follow = memberRepository.findById(followMemberId).orElseThrow { throw IllegalArgumentException("존재하지 않는 회원입니다.") }

        if (followRepository.existsByFollowerAndFollowing(member, follow)) {
            followRepository.save(Follow(member, follow))
        } else {
            throw IllegalArgumentException("이미 팔로우한 회원입니다.")
        }
    }

    // 팔로우 삭제
    @Transactional
    fun deleteFollow(memberId: Long, followMemberId: Long) {
        val member = memberRepository.findById(memberId).orElseThrow { throw IllegalArgumentException("존재하지 않는 회원입니다.") }
        val follow = memberRepository.findById(followMemberId).orElseThrow { throw IllegalArgumentException("존재하지 않는 회원입니다.") }

        if (followRepository.existsByFollowerAndFollowing(member, follow)) {
            followRepository.deleteByFollowerAndFollowing(member, follow)
        } else {
            throw IllegalArgumentException("팔로우하지 않은 회원입니다.")
        }
    }

    // 팔로우 여부 확인
    @Transactional(readOnly = true)
    fun checkFollow(memberId: Long, followMemberId: Long): Boolean {
        val member = memberRepository.findById(memberId).orElseThrow { throw IllegalArgumentException("존재하지 않는 회원입니다.") }
        val follow = memberRepository.findById(followMemberId).orElseThrow { throw IllegalArgumentException("존재하지 않는 회원입니다.") }

        return followRepository.existsByFollowerAndFollowing(member, follow)
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