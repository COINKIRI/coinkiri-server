package coinkiri.common.exception

import org.springframework.http.HttpStatusCode


enum class ExceptionCode(// 상태 코드 - HttpStatusCode Value
    val statusCode: HttpStatusCode, // 응답 설명 메세지
    val message: String
) {

    BAD_GATEWAY_EXCEPTION(HttpStatusCode.valueOf(502), "소셜 로그인 연동 중 에러가 발생하였습니다."),

    INTERNAL_SERVER_EXCEPTION(HttpStatusCode.valueOf(500), "인증이 필요한 컨트롤러 입니다. @Auth 어노테이션을 붙여주세요."),

    INTERNAL_SERVER_EXCEPTION2(HttpStatusCode.valueOf(500), "MEMBER_ID 를 가져오지 못했습니다."),
}
