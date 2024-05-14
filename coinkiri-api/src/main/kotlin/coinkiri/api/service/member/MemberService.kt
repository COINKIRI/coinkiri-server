package coinkiri.api.service.member

import coinkiri.api.controller.member.dto.request.RegisterDto
import coinkiri.api.controller.member.dto.response.MemberInfoDto
import coinkiri.core.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService (
    private val memberRepository: MemberRepository
){

    // 회원 정보 저장 API
    @Transactional
    fun saveMember(request: RegisterDto) {
        if (memberRepository.existsBySocialId(request.socialId)) { // 소셜 아이디 중복 체크
            throw IllegalArgumentException("이미 존재하는 소셜 아이디입니다.")
        }
        memberRepository.save(request.toEntity()) // 회원 정보 저장
    }

    // 회원 확인 API
    @Transactional(readOnly = true)
    fun checkMember(socialId: String): Boolean {
        return memberRepository.existsBySocialId(socialId) // 소셜 아이디로 회원 확인
    }

    // 닉네임 수정 API
    @Transactional
    fun updateNickname(socialId: String, newNickname: String) {
        val member = memberRepository.findBySocialId(socialId) ?: throw IllegalArgumentException("존재하지 않는 소셜 아이디입니다.")
        member.updateNickname(newNickname)
        memberRepository.save(member)
    }

    // 마이페이지 (회원 정보 조회) API
    @Transactional(readOnly = true)
    fun findMemberInfo(socialId: String): MemberInfoDto {
        val member = memberRepository.findBySocialId(socialId) ?: throw IllegalArgumentException("존재하지 않는 소셜 아이디입니다.")
        return MemberInfoDto( // toDto를 쓸 수 없는 이유: core모듈은 api모듈을 모르기 때문(의존성이 없기 때문)
            nickname = member.nickname,
            exp = member.exp,
            level = member.level,
            mileage = member.mileage,
            pic = member.pic ?: byteArrayOf() // null이면 빈 배열로 초기화(기본 프로필 사진 추가 예정)
        )
    }



}