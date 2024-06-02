package coinkiri.core.domain.post.analysis;

import lombok.Getter;

@Getter
public enum OpinionType {

	STRONG_BUY("강력매수"),
	BUY("매수"),
	SELL("매도"),
	HOLD("중립"),
	STRONG_SELL("강력매도");

	private final String value;

	OpinionType(String value) {
		this.value = value;
	}
}
