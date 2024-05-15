package coinkiri.api.client.kakao

import coinkiri.api.client.kakao.dto.KakaoProfileResponseDto
import coinkiri.common.exception.CoinkiriException
import coinkiri.common.exception.ExceptionCode
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class KakaoApiCallerImpl (
    private val webClient: WebClient
): KakaoApiCaller {

    override fun getProfileInfo(accessToken: String): KakaoProfileResponseDto {
        return webClient.get()
            .uri("https://kapi.kakao.com/v2/user/me")
            .headers { it.setBearerAuth(accessToken) }
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) {
                throw CoinkiriException( // custom exception 던짐
                    ExceptionCode.VALIDATION_INVALID_TOKEN_EXCEPTION
                )
            }
            .onStatus(HttpStatusCode::is5xxServerError) {
                throw CoinkiriException(
                    ExceptionCode.BAD_GATEWAY_EXCEPTION
                )
            }
            .bodyToMono(KakaoProfileResponseDto::class.java)
            .block()!!
    }
}