package coinkiri.api.service.interest

import coinkiri.api.controller.UpbitApiCaller
import coinkiri.api.controller.coin.dto.response.CoinPricesDto
import coinkiri.common.KotlinLogging.log
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
    private val coinRepository: CoinRepository,
    private val upbitApiCaller: UpbitApiCaller
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

    // 관심 종목 조회
    /**
     * member, coin, interest 3개 fetch join
     */
    @Transactional(readOnly = true)
    fun findInterestList(memberId: Long): CoinPricesDto {
        val interestCoinList = interestRepository.findWithMemberAndCoinByMemberId(memberId).map {
            it.coin.market
        }
        val coinPrices = upbitApiCaller.getCoinPrices200(interestCoinList)
        return coinPrices
    }
}