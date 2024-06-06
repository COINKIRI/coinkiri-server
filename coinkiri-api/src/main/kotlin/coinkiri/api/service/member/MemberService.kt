package coinkiri.api.service.member

import coinkiri.api.controller.member.dto.request.MemberRequestDto
import coinkiri.api.controller.member.dto.request.UpdateMemberRequestDto
import coinkiri.api.controller.member.dto.response.MemberDetailResponseDto
import coinkiri.common.exception.CoinkiriException
import coinkiri.common.exception.ExceptionCode
import coinkiri.core.domain.follow.repository.FollowRepository
import coinkiri.core.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService (
    private val memberRepository: MemberRepository,
    private val followRepository: FollowRepository
){

    // 회원 정보 저장 서비스
    @Transactional
    fun registerUser(request: MemberRequestDto): Long {
        // 소셜 아이디로 회원 조회 후 있으면 memberID 반환, 없으면 회원 저장 후 memberID 반환
        return memberRepository.findBySocialId(request.socialId)?.id ?: memberRepository.save(request.toEntity()).id!!
    }

    // 회원 정보 조회(마이페이지, 다른 유저) 서비스
    @Transactional(readOnly = true)
    fun findMemberInfo(memberId: Long): MemberDetailResponseDto {
        val member = memberRepository.findById(memberId).get() // 회원 조회

        return MemberDetailResponseDto( // toDto를 쓸 수 없는 이유: core모듈은 api모듈을 모르기 때문(의존성이 없기 때문)
            id = member.id,
            nickname = member.nickname,
            exp = member.exp,
            level = member.level,
            mileage = member.mileage,
            pic = member.pic ?: byteArrayOf(), // pic이 null이면 빈 배열로 초기화(기본 프로필 사진 추가 예정)
            statusMessage = member.statusMessage,
            followingCount = followRepository.countByFollowerId(memberId),
            followerCount = followRepository.countByFollowingId(memberId)
        )
    }

    // 회원 탈퇴 서비스
    @Transactional
    fun withdraw(memberId: Long) {
        val member = memberRepository.findById(memberId).get() // 회원 조회
        memberRepository.delete(member)
    }

    // 회원 정보 수정 서비스
    @Transactional
    fun updateMemberInfo(memberId: Long, request: UpdateMemberRequestDto) {
        val member = memberRepository.findById(memberId).get() // 회원 조회
        member.updateInfo(
            request.nickname,
            request.statusMessage,
            request.pic.toByteArray()
        ) // 회원 정보 수정
    }






}