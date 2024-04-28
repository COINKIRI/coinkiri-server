package coinkiri.api

import coinkiri.core.log.logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @ClassName    : $ 클래스에 대한 설명을 작성합니다.
 *
 */
@RestController
@RequestMapping("/log")
class LogController {

    @GetMapping
    fun getCount(): String {
        logger.info { "hello world" }
        return "HelloWorld"
    }
}