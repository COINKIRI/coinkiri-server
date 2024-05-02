package coinkiri.core.domain.user;

import lombok.Getter;

@Getter
public enum SocialType {
	KAKAO("카카오"),
	NAVER("네이버");

	private final String value;

	SocialType(String value) {
		this.value = value;
	}
}
