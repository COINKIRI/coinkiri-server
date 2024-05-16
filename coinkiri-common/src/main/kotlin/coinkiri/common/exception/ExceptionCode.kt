package coinkiri.common.exception

import org.springframework.http.HttpStatusCode


enum class ExceptionCode(// 상태 코드 - HttpStatusCode Value
    val statusCode: HttpStatusCode, // 응답 설명 메세지
    val message: String
) {

    USER_NOT_FOUND(HttpStatusCode.valueOf(404), "해당 유저가 존재하지 않습니다."),

    VALIDATION_INVALID_TOKEN_EXCEPTION(HttpStatusCode.valueOf(400), "잘못된 토큰입니다."),

    BAD_GATEWAY_EXCEPTION(HttpStatusCode.valueOf(502), "소셜 로그인 연동 중 에러가 발생하였습니다."),
}
