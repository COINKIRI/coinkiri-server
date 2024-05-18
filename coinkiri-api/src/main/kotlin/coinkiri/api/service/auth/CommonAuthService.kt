package coinkiri.api.service.auth

import coinkiri.api.service.auth.jwt.JwtHandler
import coinkiri.core.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CommonAuthService(
    private val memberRepository: MemberRepository,
    private val jwtHandler: JwtHandler
) {

    @Transactional
    fun logout(memberId: Long) {
        val member = memberRepository.findById(memberId).get()
        jwtHandler.expireRefreshToken(member.id!!)
    }
}