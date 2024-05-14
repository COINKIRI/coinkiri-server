package coinkiri.core.domain.post;

import coinkiri.core.domain.coin.Coin;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Analysis extends Post {

	// 대상 코인
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coin_id")
	private Coin coin;

	// 투자 의견
	@Column(name = "opinion", nullable = false)
	private String opinion;

	// 목표 기간
	@Column(name = "target_period", nullable = false)
	private String targetPeriod;

	// 목표 가격
	@Column(name = "target_price", nullable = false)
	private String targetPrice;
}
