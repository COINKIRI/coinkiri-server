package coinkiri.api.controller.talk

import coinkiri.api.service.talk.TalkService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Talk")
@RestController
@RequestMapping("/api/v1/talk")
class TalkController (
    private val talkService: TalkService
){

}