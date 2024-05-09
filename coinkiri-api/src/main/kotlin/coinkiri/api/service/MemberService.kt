package coinkiri.api.service

import coinkiri.api.controller.member.dto.request.RegisterDto
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

    // 닉네임 수정 API
    @Transactional
    fun updateNickname(socialId: String) {
        val member = memberRepository.findBySocialId(socialId) ?: throw IllegalArgumentException("존재하지 않는 소셜 아이디입니다.")
        memberRepository.save(member)
    }


}