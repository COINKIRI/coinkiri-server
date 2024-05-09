package coinkiri.common.exception

import org.springframework.http.HttpStatusCode


enum class ExceptionCode(// 상태 코드 - HttpStatusCode Value
    val statusCode: HttpStatusCode, // 응답 설명 메세지
    val message: String
) {

    USER_NOT_FOUND(HttpStatusCode.valueOf(404), "해당 유저가 존재하지 않습니다.")
}
