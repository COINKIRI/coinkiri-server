package coinkiri.api.service.post

import coinkiri.api.controller.coin.dto.response.CoinResponseDto
import coinkiri.api.controller.post.dto.request.AnalysisRequestDto
import coinkiri.api.controller.post.dto.request.ImageDto
import coinkiri.api.controller.post.dto.response.AnalysisDetailResponseDto
import coinkiri.api.controller.post.dto.response.AnalysisResponseDto
import coinkiri.api.controller.post.dto.response.PostDetailResponseDto
import coinkiri.api.controller.post.dto.response.PostResponseDto
import coinkiri.core.domain.coin.repository.CoinRepository
import coinkiri.core.domain.image.Image
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.post.analysis.Analysis
import coinkiri.core.domain.post.analysis.OpinionType
import coinkiri.core.domain.post.analysis.repository.AnalysisRepository
import org.apache.tomcat.util.codec.binary.Base64
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
            request.coinPrevClosingPrice,
            OpinionType.valueOf(request.investmentOption),
            request.targetPrice,
            request.targetPeriod
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
                it.coinPrevClosingPrice,
                it.investmentOption.value,
                it.targetPrice,
                it.targetPeriod
            )
        }
    }


    // 분석글 상세 조회
    @Transactional(readOnly = true)
    fun findAnalysisDetail(postId: Long): AnalysisDetailResponseDto {
        val analysis = analysisRepository.findOneWithMemberAndCoinAndCommentAndLike(postId)
        return AnalysisDetailResponseDto(
            PostDetailResponseDto(
                analysis.id,
                analysis.title,
                analysis.content,
                analysis.viewCnt,
                analysis.createdAt.toString(),
                analysis.member.nickname,
                analysis.member.level,
                analysis.member.pic,
                analysis.likes.size,
                analysis.images.map {
                    ImageDto(
                        it.position,
                        Base64.encodeBase64String(it.image)
                    )
                },
                analysis.comments.size
            ),
            CoinResponseDto(
                analysis.coin.coinId,
                analysis.coin.market,
                analysis.coin.koreanName,
                analysis.coin.englishName,
                analysis.coin.symbolImage
            ),
            analysis.coinPrevClosingPrice,
            analysis.investmentOption.value,
            analysis.targetPrice,
            analysis.targetPeriod
        )
    }
}