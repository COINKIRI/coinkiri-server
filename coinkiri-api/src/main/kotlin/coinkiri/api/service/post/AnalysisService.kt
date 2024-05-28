package coinkiri.api.service.post

import coinkiri.api.controller.post.dto.request.AnalysisRequestDto
import coinkiri.core.domain.coin.repository.CoinRepository
import coinkiri.core.domain.image.Image
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.analysis.Analysis
import coinkiri.core.domain.post.analysis.OpinionType
import coinkiri.core.domain.post.analysis.repository.AnalysisRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AnalysisService (
    private val memberRepository: MemberRepository,
    private val analysisRepository: AnalysisRepository,
    private val coinRepository: CoinRepository
){

    // 분석글 작성
    @Transactional
    fun saveAnalysisPost(memberId: Long, request: AnalysisRequestDto) {

        // 작성자
        val member = memberRepository.findById(memberId).get()

        // 분석 대상 코인
        val coin = coinRepository.findById(request.coinId).get()

        // 분석글 생성
        val analysis = Analysis(
            request.postRequestDto.title,
            request.postRequestDto.content,
            member,
            coin,
            OpinionType.valueOf(request.opinion),
            request.targetPeriod,
            request.targetPrice
        )

        // 이미지 추가 + 분석글과 연관관계 설정
        request.postRequestDto.images.forEach {
            val image = Image(
                it.position,
                it.base64.toByteArray(),
                analysis
            )
            analysis.addImage(image)
        }

        // 저장
        analysisRepository.save(analysis)

    }
}