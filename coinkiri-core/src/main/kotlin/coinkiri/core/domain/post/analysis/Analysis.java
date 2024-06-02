package coinkiri.core.domain.post.analysis;

import coinkiri.core.domain.coin.Coin;
import coinkiri.core.domain.member.Member;
import coinkiri.core.domain.post.Post;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Analysis extends Post {

	// 대상 코인
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coin_id")
	private Coin coin;

	// 선택 당시 전일 종가
	@Column(name = "coin_prev_closing_price", nullable = false)
	private String coinPrevClosingPrice;

	// 투자 의견
	@Column(name = "investment_option", nullable = false)
	@Enumerated(EnumType.STRING)
	private OpinionType investmentOption;

	// 목표 가격
	@Column(name = "target_price", nullable = false)
	private String targetPrice;

	// 목표 기간
	@Column(name = "target_period", nullable = false)
	private String targetPeriod;

	@Builder
	public Analysis(String title, String content, Member member, Coin coin, String coinPrevClosingPrice, OpinionType investmentOption, String targetPrice, String targetPeriod) {
		super(title, content, member);
		this.coin = coin;
		this.coinPrevClosingPrice = coinPrevClosingPrice;
		this.investmentOption = investmentOption;
		this.targetPrice = targetPrice;
		this.targetPeriod = targetPeriod;
	}


}
