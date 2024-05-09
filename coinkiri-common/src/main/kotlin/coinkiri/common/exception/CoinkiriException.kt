package coinkiri.common.exception


open class CoinkiriException(val exceptionCode: ExceptionCode) : RuntimeException() {}
