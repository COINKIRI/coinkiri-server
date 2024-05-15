package coinkiri.api.client.kakao

import coinkiri.api.client.kakao.dto.KakaoProfileResponseDto


interface KakaoApiCaller {

    fun getProfileInfo(accessToken: String): KakaoProfileResponseDto
}