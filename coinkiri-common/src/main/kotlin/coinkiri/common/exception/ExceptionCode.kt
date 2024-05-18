package coinkiri.common.exception

import org.springframework.http.HttpStatusCode


enum class ExceptionCode(// 상태 코드 - HttpStatusCode Value
    val statusCode: HttpStatusCode, // 응답 설명 메세지
    val message: String
) {

    USER_NOT_FOUND(HttpStatusCode.valueOf(404), "해당 유저가 존재하지 않습니다."),

    VALIDATION_INVALID_TOKEN_EXCEPTION(HttpStatusCode.valueOf(400), "잘못된 토큰입니다."),

    BAD_GATEWAY_EXCEPTION(HttpStatusCode.valueOf(502), "소셜 로그인 연동 중 에러가 발생하였습니다."),

    UNAUTHORIZED_EXCEPTION(HttpStatusCode.valueOf(401), "잘못된 jwt 입니다."),

    UNAUTHORIZED_EXCEPTION2(HttpStatusCode.valueOf(401), "주어진 액세스 토큰 으로 유저 정보를 찾을 수 없습니다."),

    INTERNAL_SERVER_EXCEPTION(HttpStatusCode.valueOf(500), "인증이 필요한 컨트롤러 입니다. @Auth 어노테이션을 붙여주세요."),

    INTERNAL_SERVER_EXCEPTION2(HttpStatusCode.valueOf(500), "MEMBER_ID 를 가져오지 못했습니다."),
}
