package coinkiri.api.service.talk

import coinkiri.api.controller.talk.TalkRequestDto
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

    @Transactional
    fun saveTalk(memberId: Long, request: TalkRequestDto) {

        val member = memberRepository.findById(memberId)
            .orElseThrow { throw IllegalArgumentException("존재하지 않는 회원입니다.") }

        val coin = coinRepository.findById(request.coinId)

        talkRepository.save(
            Talk(
                request.title,
                request.content,
                coin.get(),
                member
            )
        )
    }


}