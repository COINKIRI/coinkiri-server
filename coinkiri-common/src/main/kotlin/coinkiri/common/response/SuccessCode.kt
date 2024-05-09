package coinkiri.common.response

import org.springframework.http.HttpStatusCode

enum class SuccessCode(
    val statusCode: HttpStatusCode,
    val message: String
) {
    /**
     * 200 : 요청에 성공하였습니다.
     */
    SUCCESS(HttpStatusCode.valueOf(200), "요청에 성공하였습니다."),

}