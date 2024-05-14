package coinkiri.core.domain.post;

import java.time.LocalDateTime;

import coinkiri.core.domain.coin.Coin;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Mission extends Post {

	// 미션 종료시간
	@Column(name = "end_time", nullable = false)
	private LocalDateTime endTime;

	// 대상 코인
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coin_id")
	private Coin coin;

}
