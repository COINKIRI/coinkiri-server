package coinkiri.api.service.talk

import coinkiri.api.controller.member.dto.response.MemberResponseDto
import coinkiri.api.controller.talk.TalkRequestDto
import coinkiri.api.controller.talk.TalkResponseDto
import coinkiri.core.domain.coin.repository.CoinRepository
import coinkiri.core.domain.member.repository.MemberRepository
import coinkiri.core.domain.talk.Talk
import coinkiri.core.domain.talk.repository.TalkRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TalkService (
    private val talkRepository: TalkRepository,
    private val memberRepository: MemberRepository,
    private val coinRepository: CoinRepository
){

    // 톡 작성
    @Transactional
    fun saveTalk(memberId: Long, request: TalkRequestDto) {

        val member = memberRepository.findById(memberId)
            .orElseThrow { throw IllegalArgumentException("존재하지 않는 회원입니다.") }

        val coin = coinRepository.findById(request.coinId)

        talkRepository.save(
            Talk(
                request.content,
                coin.get(),
                member
            )
        )
    }

    // 톡 전체 조회
    @Transactional(readOnly = true)
    fun findTalkList(coinId: Long): List<TalkResponseDto> {
        val coin = coinRepository.findById(coinId)
            .orElseThrow { throw IllegalArgumentException("존재하지 않는 코인입니다.") }
        val talks = talkRepository.findByCoin(coin)

        return talks.map {
            TalkResponseDto(
                it.content,
                it.createdAt.toString(),
                MemberResponseDto(
                    it.member.id,
                    it.member.nickname,
                    it.member.level,
                    it.member.pic
                )
            )
        }
    }


}