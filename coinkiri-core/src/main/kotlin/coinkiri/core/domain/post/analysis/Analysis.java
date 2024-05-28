package coinkiri.core.domain.post.analysis;

import coinkiri.core.domain.coin.Coin;
import coinkiri.core.domain.post.Post;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class Analysis extends Post {

	// 대상 코인
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coin_id")
	private Coin coin;

	// 투자 의견
	@Column(name = "opinion", nullable = false)
	@Enumerated(EnumType.STRING)
	private OpinionType opinion;

	// 목표 기간
	@Column(name = "target_period", nullable = false)
	private int targetPeriod;

	// 목표 가격
	@Column(name = "target_price", nullable = false)
	private double targetPrice;
}
