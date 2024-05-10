package coinkiri.common

import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging

object KotlinLogging {
    val <reified T> T.log: KLogger
        inline get() = KotlinLogging.logger {}
}