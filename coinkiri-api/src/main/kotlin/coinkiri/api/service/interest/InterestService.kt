package coinkiri.api.service.interest

import coinkiri.api.controller.UpbitApiCaller
import coinkiri.api.controller.coin.dto.response.CoinPricesDto
import coinkiri.api.controller.coin.dto.response.CoinResponseDto
import coinkiri.api.controller.post.dto.response.AnalysisResponseDto
import coinkiri.api.controller.post.dto.response.PostResponseDto
import coinkiri.common.KotlinLogging.log
import coinkiri.core.domain.coin.repository.CoinRepository
import coinkiri.core.domain.interest.Interest
import coinkiri.core.domain.interest.repository.InterestRepository
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.analysis.repository.AnalysisRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InterestService (
    private val interestRepository: InterestRepository,
    private val memberRepository: MemberRepository,
    private val coinRepository: CoinRepository,
    private val analysisRepository: AnalysisRepository,
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

    // 관심 종목 분석글 조회
    @Transactional(readOnly = true)
    fun findInterestAnalysis(memberId: Long): List<AnalysisResponseDto> {
        val interestCoinList = interestRepository.findWithMemberAndCoinByMemberId(memberId).map {
            it.coin.coinId
        }
        return analysisRepository.findAnalysisByCoinList(interestCoinList).map {
            AnalysisResponseDto(
                PostResponseDto(
                    it.id,
                    it.title,
                    it.viewCnt,
                    it.createdAt.toString(),
                    it.member.nickname,
                    it.member.level,
                    it.comments.size,
                    it.likes.size
                ),
                CoinResponseDto(
                    it.coin.coinId,
                    it.coin.market,
                    it.coin.koreanName,
                    it.coin.englishName,
                    it.coin.symbolImage
                ),
                it.member.pic,
                it.coinPrevClosingPrice,
                it.investmentOption.value,
                it.targetPrice,
                it.targetPeriod
            )
        }
    }
}