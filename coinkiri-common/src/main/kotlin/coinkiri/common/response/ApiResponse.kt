package coinkiri.common.response

import coinkiri.common.exception.ExceptionCode
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.http.HttpStatusCode

/**
 * @ClassName    : BaseResponse 클래스에 대한 설명을 작성합니다.
 */
@JsonPropertyOrder("code", "message", "result")
class ApiResponse<T> {
    @JsonProperty("code")
    private val code: HttpStatusCode // 상태 코드 메세지

    @JsonProperty("message")
    private val message: String // 응답 설명 메세지

    @JsonProperty("result")
    private var result: T? = null // 결과

    /**
     * 요청 성공 시
     * @param result 응답할 값을 담은 객체
     */
    constructor(result: T) {
        this.code = SuccessCode.SUCCESS.statusCode
        this.message = SuccessCode.SUCCESS.message
        this.result = result
    }

    /**
     * 요청 성공 시
     * 응답 data가 없는 경우
     */
    constructor() {
        this.code = SuccessCode.SUCCESS.statusCode
        this.message = SuccessCode.SUCCESS.message
        this.result = null
    }

    /**
     * 요청 실패 시 - 커스텀 예외에 대한 응답
     * @param status 실패 상태
     * - 커스텀 정의한 ExceptionCode 타입 중 하나 입력
     */
    constructor(status: ExceptionCode) {
        this.code = status.statusCode
        this.message = status.message
    }

    /**
     * 요청 실패 시 - 이미 정의된 예외에 대한 응답
     * - 각각 실패 상태를 직접 지정할 때 사용
     * @param statusCode 상태 코드
     * @param message 메시지
     */
    constructor(statusCode: HttpStatusCode, message: String) {
        this.code = statusCode
        this.message = message
    }


}
