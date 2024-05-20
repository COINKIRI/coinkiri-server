package coinkiri.api.service.member

import coinkiri.api.config.resolver.MemberID
import coinkiri.api.controller.member.dto.request.RegisterRequestDto
import coinkiri.api.controller.member.dto.response.MemberInfoDto
import coinkiri.core.domain.member.SocialType
import coinkiri.core.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService (
    private val memberRepository: MemberRepository
){

    // 회원 정보 저장 서비스
    @Transactional
    fun registerUser(request: RegisterRequestDto): Long {
        // 소셜 아이디로 회원 조회 후 있으면 ID 반환, 없으면 회원 저장 후 ID 반환
        return memberRepository.findBySocialId(request.socialId)?.id ?: memberRepository.save(request.toEntity()).id!!
    }


    // 회원 로그인 서비스
    @Transactional
    fun login(socialId: String, socialType: SocialType): Long {
        val member = memberRepository.findBySocialIdAndSocialType(socialId, socialType) ?: throw IllegalArgumentException("존재하지 않는 유저입니다.")
        return member.id!! // 회원 ID 반환
    }


    // 마이페이지 (회원 정보 조회) 서비스
    @Transactional(readOnly = true)
    fun findMemberInfo(memberId: Long): MemberInfoDto {
        val member = memberRepository.findById(memberId).get()
        return MemberInfoDto( // toDto를 쓸 수 없는 이유: core모듈은 api모듈을 모르기 때문(의존성이 없기 때문)
            nickname = member.nickname,
            exp = member.exp,
            level = member.level,
            mileage = member.mileage,
            pic = member.pic ?: byteArrayOf() // pic이 null이면 빈 배열로 초기화(기본 프로필 사진 추가 예정)
        )

    }





}