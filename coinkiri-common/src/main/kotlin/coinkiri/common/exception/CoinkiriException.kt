package coinkiri.common.exception

import org.springframework.http.HttpStatusCode


class CoinkiriException : RuntimeException {
    private val statusCode: HttpStatusCode
    override var message: String

    constructor(exceptionCode: ExceptionCode) : super(exceptionCode.message) {
        this.statusCode = exceptionCode.statusCode
        this.message = exceptionCode.message
    }

    constructor(statusCode: HttpStatusCode, message: String) : super(message) {
        this.statusCode = statusCode
        this.message = message
    }
}