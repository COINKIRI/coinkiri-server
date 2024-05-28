package coinkiri.core.domain.post.analysis;

import lombok.Getter;

@Getter
public enum OpinionType {
	BUY("매수"),
	SELL("매도"),
	HOLD("중립");

	private final String value;

	OpinionType(String value) {
		this.value = value;
	}
}
