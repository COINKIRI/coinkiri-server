package coinkiri.common.exception


import coinkiri.common.response.ApiResponse
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.yaml.snakeyaml.constructor.DuplicateKeyException

/**
 * @ClassName    : GlobalExceptionHandler 클래스에 대한 설명을 작성합니다.
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    // 커스텀 예외 처리
    @ExceptionHandler(CoinkiriException::class)
    fun handleCoinkiriException(exception: CoinkiriException): ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity
            .status(exception.statusCode) // 상태 코드
            .body(ApiResponse.error(exception.statusCode, exception.message)) // 발생 시 응답
    }

    // 중복키 예외 처리
    @ExceptionHandler(DuplicateKeyException::class)
    fun handleDuplicateKeyException(exception: DuplicateKeyException): ResponseEntity<ApiResponse<String>> {
        return ResponseEntity
            .status(HttpStatusCode.valueOf(409)) // 409 Conflict
            .body(ApiResponse(
                    HttpStatusCode.valueOf(409),
                    "DuplicateKeyException 중복키가 발생하였습니다."
                )
            ) // 중복키 예외 발생 시 응답
    }

    // 유효성 검사 실패 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun exceptionHandler(exception: MethodArgumentNotValidException): ResponseEntity<ApiResponse<String>> {
        // MethodArgumentNotValidException의 경우는 메세지를 바인딩 결과에서 가져와야 함

        val bindingResult: BindingResult = exception.bindingResult
        val builder = StringBuilder()
        for (fieldError in bindingResult.fieldErrors) { // 유효성 검사 에러 메세지를 모두 builder에 담기
            builder.append(fieldError.defaultMessage)
        }

        return ResponseEntity
            .status(HttpStatusCode.valueOf(400)) // 400 Bad Request
            .body(
                ApiResponse(
                    HttpStatusCode.valueOf(400),
                    builder.toString()
                )
            ) // MethodArgumentNotValidException 발생 시 응답
    }
}
