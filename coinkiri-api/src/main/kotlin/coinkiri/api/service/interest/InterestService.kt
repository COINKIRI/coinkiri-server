package coinkiri.api.service.interest

import coinkiri.core.domain.coin.repository.CoinRepository
import coinkiri.core.domain.interest.Interest
import coinkiri.core.domain.interest.repository.InterestRepository
import coinkiri.core.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InterestService (
    private val interestRepository: InterestRepository,
    private val memberRepository: MemberRepository,
    private val coinRepository: CoinRepository
){

    // 관심 종목 등록
    @Transactional
    fun saveInterest(memberId: Long, coinId: Long) {
        val member = memberRepository.findById(memberId).get()
        val coin = coinRepository.findById(coinId).get()
        interestRepository.save(
            Interest(member, coin)
        )
    }
}