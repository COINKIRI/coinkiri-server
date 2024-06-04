package coinkiri.api.service.talk

import coinkiri.core.domain.talk.repository.TalkRepository
import org.springframework.stereotype.Service

@Service
class TalkService (
    private val talkRepository: TalkRepository
){
}