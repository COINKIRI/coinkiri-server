package coinkiri.api.service.post

import coinkiri.api.controller.coin.dto.response.CoinResponseDto
import coinkiri.api.controller.post.dto.request.AnalysisRequestDto
import coinkiri.api.controller.post.dto.response.AnalysisResponseDto
import coinkiri.api.controller.post.dto.response.PostResponseDto
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

    // 분석글 전체 조회
    @Transactional(readOnly = true)
    fun findAllAnalysis() : List<AnalysisResponseDto> {
        return analysisRepository.findAllWithMemberAndCoinAndCommentAndLike().map {
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
                it.opinion.name,
                it.targetPeriod,
                it.targetPrice
            )
        }
    }














}