package coinkiri.core.domain.post;

import lombok.Getter;

@Getter
public enum CategoryType {
	FREE("자유"),
	CHAT("잡담"),
	INFO("정보");

	private final String value;

	CategoryType(String value) {
		this.value = value;
	}
}
