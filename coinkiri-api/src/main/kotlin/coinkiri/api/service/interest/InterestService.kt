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
        if (interestRepository.existsByMemberAndCoin(member, coin)) {
            log.info { "이미 관심 종목으로 등록된 종목입니다. memberId: $memberId, coinId: $coinId" }
            return
        } else {
            log.info { "관심 종목으로 등록합니다. memberId: $memberId, coinId: $coinId" }
            interestRepository.save(Interest(member, coin))
        }

    }

    // 관심 등록 여부
    @Transactional(readOnly = true)
    fun checkInterest(memberId: Long, coinId: Long): Boolean {
        val member = memberRepository.findById(memberId).get()
        val coin = coinRepository.findById(coinId).get()
        return interestRepository.existsByMemberAndCoin(member, coin)
    }

    // 관심 종목 삭제
    @Transactional
    fun deleteInterest(memberId: Long, coinId: Long) {
        val member = memberRepository.findById(memberId).get()
        val coin = coinRepository.findById(coinId).get()
        if (!interestRepository.existsByMemberAndCoin(member, coin)) {
            log.info { "관심 종목으로 등록되어있지 않은 종목입니다. memberId: $memberId, coinId: $coinId" }
            return
        } else {
            log.info { "관심 종목에서 삭제합니다. memberId: $memberId, coinId: $coinId" }
            interestRepository.deleteByMemberAndCoin(member, coin)
        }
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
        val coinPrices = upbitApiCaller.getCoinPrices24(interestCoinList)
        return coinPrices
    }
}