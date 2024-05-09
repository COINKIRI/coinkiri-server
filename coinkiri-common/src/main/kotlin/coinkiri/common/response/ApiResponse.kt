package coinkiri.common.response

import coinkiri.common.exception.ExceptionCode
import org.springframework.http.HttpStatusCode
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

/**
 * @ClassName: ApiResponse
 * @Description: API 응답에 대한 클래스입니다.
 *               HTTP 상태 코드, 메시지 및 결과를 포함합니다.
 */
@JsonPropertyOrder("code", "message", "result")
class ApiResponse<T>(
    @JsonProperty("code") val code: HttpStatusCode, // 상태 코드
    @JsonProperty("message") val message: String,   // 응답 설명 메시지
    @JsonProperty("result") var result: T? = null   // 결과
) {
    companion object {
        /**
         * 성공 응답 생성 - 결과 포함
         */
        fun <T> success(result: T): ApiResponse<T> {
            return ApiResponse(SuccessCode.SUCCESS.statusCode, SuccessCode.SUCCESS.message, result)
        }

        /**
         * 성공 응답 생성 - 결과 미포함
         */
        fun success(): ApiResponse<Any> {
            return ApiResponse(SuccessCode.SUCCESS.statusCode, SuccessCode.SUCCESS.message, null)
        }

        /**
         * 실패 응답 생성 - 커스텀 예외 타입
         */
        fun error(status: ExceptionCode): ApiResponse<Any> {
            return ApiResponse(status.statusCode, status.message)
        }

        /**
         * 실패 응답 생성 - HTTP 상태 코드, 메시지
         */
        fun error(statusCode: HttpStatusCode, message: String): ApiResponse<Any> {
            return ApiResponse(statusCode, message)
        }
    }
}
